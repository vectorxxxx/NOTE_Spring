package com.vectorx.spring5.s9_xml.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowire {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean8.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
