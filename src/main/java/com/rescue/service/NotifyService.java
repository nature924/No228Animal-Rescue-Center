package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Notify;

import java.util.List;
import java.util.Map;


public interface NotifyService {
    void deleteNotify(Integer id);

    void addNotify(Notify notify);

    void updateNotify(Notify notify);

   Notify getNotifyById(Integer id);

    PageInfo<Notify> findNotify(Notify notify, Integer pageNum, Integer pageSize);

    List<Notify> selectAll(Map map);

}
