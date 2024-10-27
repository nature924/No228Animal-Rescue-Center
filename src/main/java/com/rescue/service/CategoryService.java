package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Category;

import java.util.List;


public interface CategoryService {
    void deleteCategory(Integer id);

    void addCategory(Category category);

    void updateCategory(Category category);

   Category getCategoryById(Integer id);

    PageInfo<Category> findCategory(Category category, Integer pageNum, Integer pageSize);

    List<Category> selectAll();

}
