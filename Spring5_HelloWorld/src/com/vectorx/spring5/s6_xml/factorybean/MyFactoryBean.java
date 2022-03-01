package com.vectorx.spring5.s6_xml.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean
 */
public class MyFactoryBean implements FactoryBean<Course> {
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("CourseName");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
