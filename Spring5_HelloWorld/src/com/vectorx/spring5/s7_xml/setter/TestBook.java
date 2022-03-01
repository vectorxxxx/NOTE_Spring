package com.vectorx.spring5.s7_xml.setter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBook {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");
        Book book1 = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);
        System.out.println(book1 == book2); // true 表示是同一个对象，证明默认情况下是单实例

        Book book3 = context.getBean("book2", Book.class);
        Book book4 = context.getBean("book2", Book.class);
        System.out.println(book3 == book4); // false 表示不是同一个对象，证明scope为prototype时是多实例
    }
}
