package com.vectorx.spring5.s0_testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring5 {

    @Test
    public void testAdd() {
        // 1、加载自定义的Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2、获取配置的User对象
        User user = context.getBean("user", User.class);

        // 3、操作User对象
        System.out.println(user);
        user.add();
    }
}
