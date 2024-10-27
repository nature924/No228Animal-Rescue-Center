package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.CategoryDao;
import com.rescue.entity.Category;
import com.rescue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryDao dao;

    @Override
    public void deleteCategory(Integer id) {
        dao.deleteCategory(id);
    }


    @Override
    public void addCategory(Category category) {
        dao.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return   dao.getCategoryById(id);

    }

    @Override
    public PageInfo<Category> findCategory(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list = dao.findCategory(category);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Category> selectAll() {
        return  dao.selectAll();
    }

}
