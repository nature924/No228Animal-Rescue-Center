package com.rescue.service;

import com.rescue.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    void deleteUser(Integer id);

    void updateUser(User user);

    PageInfo<User> findUser(User user, Integer pageNum, Integer pageSize);

    void addUser(User user);

    User selectUserByPhone(String phone);

    User getUserById(Integer id);

    void updateUserPass(User user);

    void updateUserTx(User user);
}
