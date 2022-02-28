package com.vectorx.spring5.s1_xml.setter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBook {
    public static void main(String[] args) {
        // 1、加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2、获取配置创建的对象
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        Book book1 = context.getBean("book1", Book.class);
        System.out.println(book1);
        Book book2 = context.getBean("book2", Book.class);
        System.out.println(book2);
        Book book3 = context.getBean("book3", Book.class);
        System.out.println(book3);
        Book book4 = context.getBean("book4", Book.class);
        System.out.println(book4);
    }
}
