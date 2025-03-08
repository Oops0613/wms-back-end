package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.NoticeListDto;
import com.lizekai.wms.domain.entity.*;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.NoticeMapper;
import com.lizekai.wms.domain.entity.Notice;
import com.lizekai.wms.mapper.ReadStatusMapper;
import com.lizekai.wms.service.NoticeRoleService;
import com.lizekai.wms.service.NoticeService;
import com.lizekai.wms.service.ReadStatusService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.utils.RedisCache;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 公告表(Notice)表服务实现类
 *
 * @author makejava
 * @since 2025-02-24 12:31:21
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private NoticeRoleService noticeRoleService;
    @Autowired
    private ReadStatusService readStatusService;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ReadStatusMapper readStatusMapper;

    @Override
    public ResponseResult getNoticeList(NoticeListDto dto, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        String keyword = dto.getKeyWord();
        wrapper.like(StringUtils.hasText(keyword), Notice::getTitle, keyword);
        wrapper.or().like(StringUtils.hasText(keyword), Notice::getContent, keyword);
        Page<Notice> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        page.getRecords().forEach(notice -> {
            LambdaQueryWrapper<NoticeRole> wrapper2=new LambdaQueryWrapper<>();
            wrapper2.eq(NoticeRole::getNoticeId,notice.getId());
            List<Long> roleIds = noticeRoleService.list(wrapper2)
                    .stream()
                    .map(NoticeRole::getRoleId)
                    .collect(Collectors.toList());
            notice.setRoleList(roleIds);
        });
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertNotice(Notice notice) {
        List<Long> roleList = notice.getRoleList();
        //找出所有的收件人用户ID
        if (CollectionUtils.isEmpty(roleList)) {
            throw new SystemException(AppHttpCodeEnum.ROLE_NOT_NULL);
        }
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.in(User::getRoleId, roleList);
        List<Long> userIds = userService.list(userWrapper)
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
        //插入主表
        save(notice);
        //插入多对多关系表
        Long noticeId=notice.getId();
        List<NoticeRole> noticeRoles = roleList.stream()
                .map(roleId -> new NoticeRole(noticeId, roleId))
                .collect(Collectors.toList());
        noticeRoleService.saveBatch(noticeRoles);
        //更新redis未读状态
        Set<Long> noticeSet=new HashSet<>();
        noticeSet.add(noticeId);
        userIds.forEach(userId->{
            redisCache.setCacheSet("WMSUnread:" + userId,noticeSet);
        });
        //更新已读状态表
        List<ReadStatus> unreadList = userIds.stream()
                .map(userId -> new ReadStatus(noticeId, userId))
                .collect(Collectors.toList());
        readStatusService.saveBatch(unreadList);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateNotice(Notice notice) {
        updateById(notice);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult removeNotice(Long id) {
        //删除主表
        removeById(id);
        //删除关系表
        LambdaQueryWrapper<NoticeRole> noticeRoleWrapper=new LambdaQueryWrapper<>();
        noticeRoleWrapper.eq(NoticeRole::getNoticeId,id);
        List<Long> roleList = noticeRoleService.list(noticeRoleWrapper)
                .stream()
                .map(NoticeRole::getRoleId)
                .collect(Collectors.toList());
        noticeRoleService.remove(noticeRoleWrapper);
        //删除已读状态表
        LambdaQueryWrapper<ReadStatus> readStatusWrapper=new LambdaQueryWrapper<>();
        readStatusWrapper.eq(ReadStatus::getNoticeId,id);
        readStatusService.remove(readStatusWrapper);
        //删除redis相关内容
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.in(User::getRoleId, roleList);
        List<Long> userIds = userService.list(userWrapper)
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
        Set<Long> noticeSet=new HashSet<>();
        noticeSet.add(id);
        userIds.forEach(userId-> {
            redisCache.removeCacheSet("WMSUnread:" + userId,noticeSet);
        });
        return ResponseResult.okResult();
    }

    /**
     * 获取该用户信箱里最新的一条公告
     * @return
     */
    @Override
    public ResponseResult getLatestNotice() {
        User user = SecurityUtils.getLoginUser().getUser();
        List<Notice> notice = noticeMapper.getLatestNotice(user.getRoleId());
        return ResponseResult.okResult(notice);
    }

    /**
     * 分页查询该用户信箱的内容
     * @param dto
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult listPersonalNotice(NoticeListDto dto, Integer pageNum, Integer pageSize) {
        User user = SecurityUtils.getLoginUser().getUser();
        Long userId=user.getId();
        Long roleId=user.getRoleId();
        String keyWord = dto.getKeyWord();
        if(!StringUtils.hasText(keyWord)){
            keyWord=null;
        }
        Page<Notice> page = new Page<>(pageNum, pageSize);
        page = noticeMapper.listPersonalNotice(page,roleId, keyWord);
        page.getRecords().forEach(notice -> {
            Set<Object> noticeSet = redisCache.getCacheSet("WMSUnread:" + userId);
            if(noticeSet.contains(notice.getId().toString())){
                notice.setIsRead(SystemCanstants.IS_UNREAD);
            }
            else {
                notice.setIsRead(SystemCanstants.IS_READ);
            }
        });
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    /**
     * 用户查看某条公告的具体内容
     * @param noticeId
     * @return
     */
    @Override
    public ResponseResult getNoticeDetail(Long noticeId) {
        Long userId = SecurityUtils.getUserId();
        Set<Object> noticeSet = redisCache.getCacheSet("WMSUnread:" + userId);
        //如果移除成功说明该公告未读
        if(noticeSet.contains(noticeId.toString())){
            //更新数据库和Redis
            readStatusMapper.updateReadStatus(noticeId,userId);
            Set<Long> newSet=new HashSet<>();
            newSet.add(noticeId);
            redisCache.removeCacheSet("WMSUnread:" + userId,newSet);
        }
        Notice notice = getById(noticeId);
        return ResponseResult.okResult(notice);
    }

    /**
     * 获取某用户未读公告的数量
     * @return
     */
    @Override
    public ResponseResult getUnreadAmount() {
        Long userId = SecurityUtils.getUserId();
        Set<Object> noticeSet = redisCache.getCacheSet("WMSUnread:" + userId);
        return ResponseResult.okResult(noticeSet.size());
    }
}

