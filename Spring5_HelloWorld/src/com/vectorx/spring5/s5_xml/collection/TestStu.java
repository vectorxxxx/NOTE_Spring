package com.vectorx.spring5.s5_xml.collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStu {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean4.xml");
        Stu stu = applicationContext.getBean("stu", Stu.class);
        System.out.println(stu);
        stu.getLists2().forEach(r->System.out.println(r.getCname()));

        Stu stu2 = applicationContext.getBean("stu2", Stu.class);
        System.out.println(stu2);
    }
}
