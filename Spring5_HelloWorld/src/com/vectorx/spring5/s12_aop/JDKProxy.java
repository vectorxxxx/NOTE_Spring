package com.vectorx.spring5.s12_aop;

public class JDKProxy {
    public static void main(String[] args) {
        UserDao target = new UserDaoImpl();
        UserDaoProxy userDaoProxy = new UserDaoProxy(target);
        UserDao userDao = userDaoProxy.newProxyInstance();
        userDao.add(1, 2);
        userDao.update("UUID1");
    }
}
