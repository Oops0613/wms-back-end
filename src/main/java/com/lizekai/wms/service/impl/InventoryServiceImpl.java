package com.lizekai.wms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.alibaba.excel.EasyExcel;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.domain.vo.ExcelInventoryVo;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.mapper.InventoryMapper;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.service.InventoryService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.utils.BeanCopyUtils;
import com.lizekai.wms.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 库存表(Inventory)表服务实现类
 *
 * @author makejava
 * @since 2025-01-13 10:25:55
 */
@Service("inventoryService")
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {
    @Autowired
    private UserService userService;
    @Override
    public ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Inventory> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(dto.getWarehouseId()),Inventory::getWarehouseId,dto.getWarehouseId());
        wrapper.eq(Objects.nonNull(dto.getCategoryId()),Inventory::getCategoryId,dto.getCategoryId());
        wrapper.like(StringUtils.hasText(dto.getGoodsName()),Inventory::getGoodsName,dto.getGoodsName());
        //需要忽略为0的库存
        if(SystemConstants.IGNORE_ZERO.equals(dto.getIgnoreZero())){
            wrapper.gt(Inventory::getAmount,0);
        }
        //按更新时间倒序排列
        wrapper.orderByDesc(Inventory::getUpdateTime);
        Page<Inventory> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public void export(HttpServletResponse response) {
        try {
            //设置下载文件的请求头（文件名）
            String currentTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            WebUtils.setDownLoadHeader("库存信息_"+currentTime+".xlsx",response);
            //获取需要导出的数据
            List<Inventory> inventoryList = list();
            List<ExcelInventoryVo> excelInventoryVos = BeanCopyUtils.copyBeanList(inventoryList, ExcelInventoryVo.class);
            //把用户ID替换为用户名
            Map<Long, String> userMap = userService.list().stream()
                    .collect(Collectors.toMap(User::getId, User::getRealName));
            excelInventoryVos.forEach(item->{
                item.setCreateByName(userMap.get(item.getCreateBy()));
                item.setUpdateByName(userMap.get(item.getUpdateBy()));
                if(!SystemConstants.CAN_EXPIRE.equals(item.getHasExpirationTime())){
                    item.setExpirationTime(null);
                }
            });
            //把数据写入到Excel中
            //sheet方法里面的字符串是Excel表格左下角工作簿的名字
            EasyExcel.write(response.getOutputStream(), ExcelInventoryVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("库存信息")
                    .doWrite(excelInventoryVos);
        } catch (Exception e) {
            //如果出现异常,就返回失败的json数据给前端。
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            //将json字符串写入到请求体，然后返回给前端
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}

