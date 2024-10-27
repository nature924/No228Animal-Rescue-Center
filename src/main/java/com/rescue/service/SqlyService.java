package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Sqly;

import java.util.List;
import java.util.Map;


public interface SqlyService {
    void deleteSqly(Integer id);

    void addSqly(Sqly sqly);

    void updateSqly(Sqly sqly);

   Sqly getSqlyById(Integer id);

    PageInfo<Sqly> findSqly(Sqly sqly, Integer pageNum, Integer pageSize);

    List<Sqly> selectAll(Map map);

    void updateSqlyState(Sqly sqly);
}
