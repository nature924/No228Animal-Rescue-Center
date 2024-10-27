package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.AnimalDao;
import com.rescue.dao.DonateDao;
import com.rescue.dao.UserDao;
import com.rescue.entity.Donate;
import com.rescue.entity.Statics;
import com.rescue.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DonateServiceImpl implements DonateService {


    @Autowired
    DonateDao dao;

    @Autowired
    UserDao userDao;

    @Autowired
    AnimalDao animalDao;

    @Override
    public void deleteDonate(Integer id) {
        dao.deleteDonate(id);
    }


    @Override
    public void addDonate(Donate donate) {
        dao.addDonate(donate);
    }

    @Override
    public void updateDonate(Donate donate) {
        dao.updateDonate(donate);
    }

    @Override
    public Donate getDonateById(Integer id) {
        return   dao.getDonateById(id);

    }

    @Override
    public PageInfo<Donate> findDonate(Donate donate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Donate> list = dao.findDonate(donate);
        for(Donate donate1 : list){
            donate1.setAnimal(animalDao.getAnimalById(donate1.getAid()));
            donate1.setUser(userDao.getUserById(donate1.getUid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Donate> selectAll(Map state) {
        return  dao.selectAll(state);
    }

    @Override
    public void updateDonateState(Donate donate) {
        dao.updateDonateState(donate);
    }

    @Override
    public List<Statics> selectDonatesStatics() {
        return   dao.selectDonatesStatics();
    }

}
