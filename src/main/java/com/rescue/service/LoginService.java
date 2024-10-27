package com.rescue.service;

import com.rescue.entity.Admin;
import com.rescue.entity.User;

import java.sql.Driver;


public interface LoginService {
    Admin selectAdmin(String username, String password);

    User selectUser(String username, String password);


    void updateAdmin(Admin admin);
}
