package com.vectorx.spring5.s11_annotation.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl2 implements UserDao {
    @Override
    public void add() {
        System.out.println("UserDaoImpl2 add...");
    }
}
