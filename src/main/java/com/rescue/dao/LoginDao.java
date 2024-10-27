package com.rescue.dao;

import com.rescue.entity.Admin;
import com.rescue.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginDao {
    Admin selectAdmin(@Param("username") String username, @Param("password") String password);

    User selectUser(@Param("username") String username, @Param("password") String password);

    void updateAdmin(@Param("admin") Admin admin);
}
