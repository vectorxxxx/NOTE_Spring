package com.vectorx.spring5.s3_xml.outerbean;

import com.vectorx.spring5.s3_xml.outerbean.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOuterBean {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.updateUser();
    }
}
