package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Menu;
import com.lizekai.wms.domain.vo.MenuTreeVo;
import com.lizekai.wms.domain.vo.MenuVo;
import com.lizekai.wms.domain.vo.RoleMenuTreeSelectVo;
import com.lizekai.wms.service.MenuService;
import com.lizekai.wms.utils.BeanCopyUtils;
import com.lizekai.wms.utils.SystemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @SystemLog(businessName="查询菜单列表")
    public ResponseResult list(Menu menu) {
        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }


    @PostMapping
    @SystemLog(businessName="新增菜单")
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping(value = "/{menuId}")
    @SystemLog(businessName="查询菜单信息")
    public ResponseResult getInfo(@PathVariable Long menuId) {
        return ResponseResult.okResult(menuService.getById(menuId));
    }

    @PutMapping
    @SystemLog(businessName="修改菜单")
    public ResponseResult edit(@RequestBody Menu menu) {
        if (menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500,"修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    //-----------------------------------删除菜单---------------------------------------

//    @DeleteMapping("/{menuId}")
//    public ResponseResult remove(@PathVariable("menuId") Long menuId) {
//        if (menuService.hasChild(menuId)) {
//            return ResponseResult.errorResult(500,"存在子菜单不允许删除");
//        }
//        menuService.removeById(menuId);
//        return ResponseResult.okResult();
//    }

    @GetMapping("/treeSelect")
    @SystemLog(businessName="查询菜单树")
    public ResponseResult treeSelect() {
        //复用之前的selectMenuList方法。方法需要参数，参数可以用来进行条件查询，而这个方法不需要条件，所以直接new Menu()传入
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<MenuTreeVo> options =  SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(options);
    }
    @GetMapping("/selectRouteTree")
    @SystemLog(businessName="查询路由树")
    public ResponseResult selectRouteTree() {
        //查询出需要展示在侧边栏的路由
        List<MenuTreeVo> options = menuService.selectRouteTree();
        return ResponseResult.okResult(options);
    }
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    @SystemLog(businessName="根据角色查询菜单树")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys,menuTreeVos);
        return ResponseResult.okResult(vo);
    }
}
