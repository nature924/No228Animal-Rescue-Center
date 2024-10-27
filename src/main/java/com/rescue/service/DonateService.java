package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Donate;
import com.rescue.entity.Statics;

import java.util.List;
import java.util.Map;


public interface DonateService {
    void deleteDonate(Integer id);

    void addDonate(Donate donate);

    void updateDonate(Donate donate);

   Donate getDonateById(Integer id);

    PageInfo<Donate> findDonate(Donate donate, Integer pageNum, Integer pageSize);

    List<Donate> selectAll(Map map);

    void updateDonateState(Donate donate);

    List<Statics> selectDonatesStatics();
}
