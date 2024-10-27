package com.rescue.service.impl;

import com.rescue.dao.LoginDao;
import com.rescue.entity.Admin;
import com.rescue.entity.User;
import com.rescue.service.LoginService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao dao;

    @Override
    public Admin selectAdmin(String username, String password) {
        return dao.selectAdmin(username,password);
    }

    @Override
    public User selectUser(String username, String password) {
        return dao.selectUser(username,password);
    }

    @Override
    public void updateAdmin(Admin admin) {
        dao.updateAdmin(admin);
    }
}
