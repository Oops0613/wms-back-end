package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Category;
import org.springframework.stereotype.Service;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-12-13 11:13:21
 */
@Service
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList(Category category);
    ResponseResult addCategory(Category category);

    ResponseResult updateCategory(Category category);

    ResponseResult getCategoryById(Long id);

    ResponseResult deleteCategory(Long id);
}

