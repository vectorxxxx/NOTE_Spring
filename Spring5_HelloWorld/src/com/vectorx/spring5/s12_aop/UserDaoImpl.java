package com.vectorx.spring5.s12_aop;

public class UserDaoImpl implements UserDao {
    @MyAnnotation(value = "2")
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public String update(String id) {
        return id;
    }
}
