package com.lizekai.wms.controller;

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
    public ResponseResult getCategoryList(Category category){
        return categoryService.getCategoryList(category);
    }
    @GetMapping("/listAvailableCategory")
    public ResponseResult getAvailableCategoryList(){
        return categoryService.getAvailableCategoryList();
    }
    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable(name = "id") Long id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping()
    public ResponseResult updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    @GetMapping("/{id}")
    public ResponseResult getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/getWarehouseComposition")
    public ResponseResult getWarehouseComposition(GetWarehouseCompositionDto dto){
        return categoryStatService.getWarehouseComposition(dto);
    }
    @GetMapping("/getSalesComposition")
    public ResponseResult getSalesComposition(GetSalesCompositionDto dto){
        return categoryStatService.getSalesComposition(dto);
    }
}
