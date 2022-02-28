package com.vectorx.spring5.s3_xml.outerbean.service;

import com.vectorx.spring5.s3_xml.outerbean.dao.UserDao;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUser(){
        System.out.println("service update...");
        userDao.update();
    }
}
