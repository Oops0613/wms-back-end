package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetSalesCompositionDto;
import com.lizekai.wms.domain.dto.GetWarehouseCompositionDto;
import com.lizekai.wms.domain.entity.Category;
import com.lizekai.wms.service.CategoryService;
import com.lizekai.wms.service.CategoryStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryStatService categoryStatService;

    @GetMapping("/list")
    @SystemLog(businessName = "查询所有分类列表")
    public ResponseResult getCategoryList(Category category){
        return categoryService.getCategoryList(category);
    }
    @GetMapping("/listAvailableCategory")
    @SystemLog(businessName = "查询可使用的分类列表")
    public ResponseResult getAvailableCategoryList(){
        return categoryService.getAvailableCategoryList();
    }
    @PostMapping
    @SystemLog(businessName = "新增分类")
    public ResponseResult addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除分类")
    public ResponseResult deleteCategory(@PathVariable(name = "id") Long id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping()
    @SystemLog(businessName = "修改分类")
    public ResponseResult updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    @GetMapping("/{id}")
    @SystemLog(businessName = "查询分类信息")
    public ResponseResult getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/getWarehouseComposition")
    @SystemLog(businessName = "查询仓库货物的分类组成")
    public ResponseResult getWarehouseComposition(GetWarehouseCompositionDto dto){
        return categoryStatService.getWarehouseComposition(dto);
    }
    @GetMapping("/getSalesComposition")
    @SystemLog(businessName = "查询销售额的分类组成")
    public ResponseResult getSalesComposition(GetSalesCompositionDto dto){
        return categoryStatService.getSalesComposition(dto);
    }
}
