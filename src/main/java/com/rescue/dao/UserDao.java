package com.rescue.dao;

import com.rescue.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    void deleteUser(@Param("id") Integer id);

    void updateUser(@Param("user") User user);

    List<User> findUser(@Param("user") User user);

    void addUser(@Param("user") User user);

    User selectUserByPhone(@Param("phone") String phone);

    User getUserById(@Param("id") Integer id);

    void updateUserPass(@Param("user") User user);

    void updateUserTx(@Param("user") User user);
}
