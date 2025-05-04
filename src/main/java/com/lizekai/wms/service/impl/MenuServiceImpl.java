package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.vo.MenuTreeVo;
import com.lizekai.wms.enums.MenuTypeEnum;
import com.lizekai.wms.mapper.MenuMapper;
import com.lizekai.wms.domain.entity.Menu;
import com.lizekai.wms.service.MenuService;
import com.lizekai.wms.utils.SecurityUtils;
import com.lizekai.wms.utils.SystemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-12-07 12:04:39
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<String> selectPermsByUserId(Long id) {
        //用户是管理员
        if(SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,
                    MenuTypeEnum.TYPE_MENU.getCode(),
                    MenuTypeEnum.TYPE_BUTTON.getCode());
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<String> perms = list(wrapper).stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        //如果不是管理员就返回对应用户所具有的权限
        List<String> otherPerms = getBaseMapper().selectPermsByOtherUserId(id);
        return otherPerms;
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        List<Menu> menus=null;
        if(SecurityUtils.isAdmin()){
            menus=menuMapper.selectAllRouterMenu();
        }
        else {
            menus=menuMapper.selectOtherRouterMenuTreeByUserId(userId);
        }
        //构建成tree，也就是子父菜单树，有层级关系
        //思路:先找出第一层的菜单，然后再找子菜单(也就是第二层)，把子菜单的结果赋值给Menu类的children字段
        List<Menu> menuTree = buildMenuTree(menus,0L);

        return menuTree;
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //menuName模糊查询
        queryWrapper.like(StringUtils.hasText(menu.getMenuName()),Menu::getMenuName,menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()),Menu::getStatus,menu.getStatus());
        //忽略按钮类型的菜单
        queryWrapper.in(Menu::getMenuType,
                Arrays.asList(MenuTypeEnum.TYPE_MENU.getCode(), MenuTypeEnum.TYPE_TOP_MENU.getCode()));
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);
        return menus;
    }

    @Override
    public boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,menuId);
        return count(queryWrapper) != 0;
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return menuMapper.selectMenuListByRoleId(roleId);
    }

    @Override
    public List<MenuTreeVo> selectRouteTree() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        List<String> menuTypes=new ArrayList<>();
        menuTypes.add(MenuTypeEnum.TYPE_MENU.getCode());
        menuTypes.add(MenuTypeEnum.TYPE_TOP_MENU.getCode());
        queryWrapper.in(Menu::getMenuType,menuTypes);
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> routes = list(queryWrapper);
        List<MenuTreeVo> options =  SystemConverter.buildMenuSelectTree(routes);
        return options;
    }

    private List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(item -> item.getParentId().equals(parentId))
                .map(item -> item.setChildren(getChildren(item, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取menus中menu的孩子
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu,List<Menu> menus){
        List<Menu> childrens=menus.stream()
                .filter(item->item.getParentId().equals(menu.getId()))
                .map(item->item.setChildren(getChildren(item,menus)))
                .collect(Collectors.toList());
        return childrens;
    }
}

