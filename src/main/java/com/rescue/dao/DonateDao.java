package com.rescue.dao;

import com.rescue.entity.Donate;
import com.rescue.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface DonateDao {
    void deleteDonate(@Param("id") Integer id);

    void addDonate(@Param("donate") Donate donate);

    void updateDonate(@Param("donate") Donate donate);

   Donate getDonateById(@Param("id") Integer id);

    List<Donate> findDonate(@Param("donate") Donate donate);

    List<Donate> selectAll(@Param("map") Map state);

    void updateDonateState(@Param("donate") Donate donate);

    List<Statics> selectDonatesStatics();
}
