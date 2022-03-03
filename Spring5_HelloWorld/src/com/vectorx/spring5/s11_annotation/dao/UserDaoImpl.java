package com.vectorx.spring5.s11_annotation.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class UserDaoImpl implements UserDao {

    @Value(value = "vector")
    private String name;
    @Value(value = "100")
    private Integer age;
    @Value(value = "200.0d")
    private Double length;
    @Value(value = "true")
    private boolean isOk;
    @Value(value = "0,a,3,6,test")
    private String[] arrs;

    @Override
    public void add() {
        System.out.println("UserDaoImpl add...");
        System.out.println("name=" + name + ", age=" + age + ", length=" + length + ", isOk=" + isOk);
        Arrays.stream(arrs).forEach(System.out::println);
    }
}
