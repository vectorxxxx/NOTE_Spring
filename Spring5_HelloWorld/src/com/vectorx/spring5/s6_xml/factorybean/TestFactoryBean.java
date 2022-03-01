package com.vectorx.spring5.s6_xml.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactoryBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean5.xml");
        MyNormalBean myNormalBean = applicationContext.getBean("myNormalBean", MyNormalBean.class);
        System.out.println(myNormalBean);
        //MyFactoryBean myFactoryBean = applicationContext.getBean("myFactoryBean", MyFactoryBean.class);
        //System.out.println(myFactoryBean);
        Course course = applicationContext.getBean("myFactoryBean", Course.class);
        System.out.println(course);
    }
}
