package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.NotifyDao;
import com.rescue.entity.Notify;
import com.rescue.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NotifyServiceImpl implements NotifyService {


    @Autowired
    NotifyDao dao;

    @Override
    public void deleteNotify(Integer id) {
        dao.deleteNotify(id);
    }


    @Override
    public void addNotify(Notify notify) {
        dao.addNotify(notify);
    }

    @Override
    public void updateNotify(Notify notify) {
        dao.updateNotify(notify);
    }

    @Override
    public Notify getNotifyById(Integer id) {
        return   dao.getNotifyById(id);

    }

    @Override
    public PageInfo<Notify> findNotify(Notify notify, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notify> list = dao.findNotify(notify);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Notify> selectAll(Map state) {
        return  dao.selectAll(state);
    }

}
