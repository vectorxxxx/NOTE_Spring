package com.vectorx.spring5.s4_xml.innerbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInnerBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean3.xml");
        Emp emp1 = applicationContext.getBean("emp1", Emp.class);
        System.out.println(emp1);
        Emp emp2 = applicationContext.getBean("emp2", Emp.class);
        System.out.println(emp2);
    }
}
