package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.AnimalDao;
import com.rescue.dao.SqlyDao;
import com.rescue.dao.UserDao;
import com.rescue.entity.Sqly;
import com.rescue.service.SqlyService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SqlyServiceImpl implements SqlyService {


    @Autowired
    SqlyDao dao;

    @Autowired
    UserDao userDao;
    @Autowired
    AnimalDao animalDao;


    @Override
    public void deleteSqly(Integer id) {
        dao.deleteSqly(id);
    }


    @Override
    public void addSqly(Sqly sqly) {
        dao.addSqly(sqly);
    }

    @Override
    public void updateSqly(Sqly sqly) {
        dao.updateSqly(sqly);
    }

    @Override
    public Sqly getSqlyById(Integer id) {
        return   dao.getSqlyById(id);

    }

    @Override
    public PageInfo<Sqly> findSqly(Sqly sqly, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Sqly> list = dao.findSqly(sqly);
        for(Sqly data :list){
            data.setUser(userDao.getUserById(data.getUid()));
            data.setAnimal(animalDao.getAnimalById(data.getAid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Sqly> selectAll(Map state) {
        return  dao.selectAll(state);
}

    @Override
    public void updateSqlyState(Sqly sqly) {
        dao.updateSqlyState(sqly);
    }

}
