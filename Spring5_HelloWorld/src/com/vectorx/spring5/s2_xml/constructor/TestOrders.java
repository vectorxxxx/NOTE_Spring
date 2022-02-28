package com.vectorx.spring5.s2_xml.constructor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOrders {
    public static void main(String[] args) {
        // 1、加载Spring配置文件
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、获取配置对象创建
        Orders orders = beanFactory.getBean("orders", Orders.class);
        System.out.println(orders);
    }
}
