package com.vectorx.spring5.s8_xml.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLiftcycle {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("Step4.获取创建Bean实例对象.");
        System.out.println(orders);
        // 手动销毁Bean实例
        context.close();
    }
}
