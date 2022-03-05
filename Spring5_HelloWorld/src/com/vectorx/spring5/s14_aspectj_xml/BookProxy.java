package com.vectorx.spring5.s14_aspectj_xml;

import org.aspectj.lang.ProceedingJoinPoint;

public class BookProxy {

    public void before() {
        System.out.println("before...");
    }

    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    public void after() {
        System.out.println("after...");
    }

    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before...");
        joinPoint.proceed();
        System.out.println("around after...");
    }
}
