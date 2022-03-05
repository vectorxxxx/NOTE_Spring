package com.vectorx.spring5.s13_aspectj_annatation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class UserProxy {
    //private final String execution = "pointcut()";

    @Pointcut(value = "execution(* com.vectorx.spring5.s13_aspectj_annatation.User.add(..))")
    private void pointcut() {
    }

    /**
     * 前置通知
     */
    @Before(value = "pointcut()")
    public void before() {
        System.out.println("user before...");
    }

    /**
     * 后置通知
     */
    @AfterReturning(value = "pointcut()")
    public void afterReturning() {
        System.out.println("user afterReturning...");
    }

    /**
     * 最终通知
     */
    @After(value = "pointcut()")
    public void after() {
        System.out.println("user after...");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        System.out.println("user afterThrowing...");
    }

    /**
     * 环绕通知
     */
    @Around(value = "pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("user around before...");
        joinPoint.proceed();
        System.out.println("user around after...");
    }
}
