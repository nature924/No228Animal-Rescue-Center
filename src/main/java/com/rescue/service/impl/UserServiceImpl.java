package com.rescue.service.impl;

import com.rescue.dao.UserDao;
import com.rescue.entity.User;
import com.rescue.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;
    @Override
    public void deleteUser(Integer id) {
        dao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public PageInfo<User> findUser(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = dao.findUser(user);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return  dao.selectUserByPhone(phone);
    }

    @Override
    public User getUserById(Integer id) {
        return dao.getUserById(id);
    }

    @Override
    public void updateUserPass(User user) {
        dao.updateUserPass(user);
    }

    @Override
    public void updateUserTx(User user) {
        dao.updateUserTx(user);
    }


}
