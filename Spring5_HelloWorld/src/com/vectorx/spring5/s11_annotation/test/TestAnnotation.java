package com.vectorx.spring5.s11_annotation.test;

import com.vectorx.spring5.s11_annotation.SpringConfig;
import com.vectorx.spring5.s11_annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnnotation {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean10.xml");
        //UserService userService = context.getBean("userService", UserService.class);
        //userService.add();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
}
