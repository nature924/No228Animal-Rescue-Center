package com.rescue.dao;

import com.rescue.entity.Notify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface NotifyDao {
    void deleteNotify(@Param("id") Integer id);

    void addNotify(@Param("notify") Notify notify);

    void updateNotify(@Param("notify") Notify notify);

   Notify getNotifyById(@Param("id") Integer id);

    List<Notify> findNotify(@Param("notify") Notify notify);

    List<Notify> selectAll(@Param("map") Map state);

}
