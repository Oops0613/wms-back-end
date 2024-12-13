package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.mapper.CategoryMapper;
import com.lizekai.wms.domain.entity.Category;
import com.lizekai.wms.service.CategoryService;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-12-13 11:13:21
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public ResponseResult getCategoryList(Category category) {
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(category.getName()),Category::getName,category.getName());
        //按父分类ID排序
        wrapper.orderByAsc(Category::getParentId,Category::getId);
        List<Category> categoryList=list(wrapper);
        return ResponseResult.okResult(categoryList);
    }

    @Override
    public ResponseResult addCategory(Category category) {
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRole(Category category) {
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategoryById(Long id) {
        return ResponseResult.okResult(getById(id));
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        Category category=getById(id);
        String pid=category.getParentId().toString();
        //要删除的是一级分类
        if(SystemCanstants.CATEGORY_ROOT.equals(pid)){
            if(hasChildren(id)){
                return ResponseResult.errorResult(500,"该分类下有子分类，不能删除");
            }
        }
        removeById(id);
        return ResponseResult.okResult();
    }
    private boolean hasChildren(Long id){
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId,id);
        return count(wrapper)>0;
    }
}

