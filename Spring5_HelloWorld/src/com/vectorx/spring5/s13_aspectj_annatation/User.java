package com.vectorx.spring5.s13_aspectj_annatation;

import org.springframework.stereotype.Component;

@Component
public class User {
    public void add() {
        System.out.println("add...");
        // 模拟一个异常
        //int i = 2 / 0;
    }
}
