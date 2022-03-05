package com.vectorx.spring5.s12_aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class UserDaoProxy {
    private UserDao target;

    public UserDaoProxy(UserDao target) {
        this.target = target;
    }

    public UserDao newProxyInstance() {
        Class<?> targetClass = target.getClass();
        ClassLoader classLoader = targetClass.getClassLoader();
        Class<?>[] interfaces = targetClass.getInterfaces();
        return (UserDao) Proxy.newProxyInstance(classLoader, interfaces, new UserDaoInvocationHandler());
    }

    class UserDaoInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 被代理对象方法前置逻辑
            System.out.print("method=" + method.getName() + ", args=" + Arrays.toString(args));
            // 被代理对象方法
            Object result = method.invoke(target, args);
            // 被代理对象方法后置逻辑
            System.out.println(", result=" + result);
            return result;
        }
    }
}
