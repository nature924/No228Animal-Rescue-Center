package com.rescue.dao;

import com.rescue.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CategoryDao {
    void deleteCategory(@Param("id") Integer id);

    void addCategory(@Param("category") Category category);

    void updateCategory(@Param("category") Category category);

   Category getCategoryById(@Param("id") Integer id);

    List<Category> findCategory(@Param("category") Category category);

    List<Category> selectAll();

}
