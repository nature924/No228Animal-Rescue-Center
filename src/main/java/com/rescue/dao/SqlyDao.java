package com.rescue.dao;

import com.rescue.entity.Sqly;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SqlyDao {
    void deleteSqly(@Param("id") Integer id);

    void addSqly(@Param("sqly") Sqly sqly);

    void updateSqly(@Param("sqly") Sqly sqly);

   Sqly getSqlyById(@Param("id") Integer id);

    List<Sqly> findSqly(@Param("sqly") Sqly sqly);

    List<Sqly> selectAll(@Param("map") Map state);

    void updateSqlyState(@Param("sqly") Sqly sqly);
}
