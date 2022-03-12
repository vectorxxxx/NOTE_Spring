package com.vectorx.spring5.s20_functional;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

public class TestGenericApplicationContext {

    @Test
    public void test1() {
        //1、创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        //2、调用context的方法进行注册
        context.refresh();
        context.registerBean(User.class, () -> new User());
        //3、获取Spring注册的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test2() {
        //1、创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        //2、调用context的方法进行注册
        context.refresh();
        context.registerBean("user", User.class, () -> new User());
        //3、获取Spring注册的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test3() {
        //1、创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        //2、调用context的方法进行注册
        context.refresh();
        context.registerBean(User.class, () -> new User());
        //3、获取Spring注册的对象
        User user = context.getBean("com.vectorx.spring5.s20_functional.User", User.class);
        System.out.println(user);
    }
}
