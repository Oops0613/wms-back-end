package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Category;
import com.lizekai.wms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 35238
 * @date 2023/7/20 0020 14:15
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    //CategoryService是我们在huanf-framework工程里面写的接口
    private CategoryService categoryService;

    @GetMapping("/list")
    //ResponseResult是我们在huanf-framework工程里面写的实体类
    public ResponseResult getCategoryList(Category category){
        return categoryService.getCategoryList(category);
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
        return categoryService.updateRole(category);
    }
    @GetMapping("/{id}")
    public ResponseResult getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }
}
