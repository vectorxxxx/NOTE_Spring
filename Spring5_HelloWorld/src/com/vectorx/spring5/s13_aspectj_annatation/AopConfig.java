package com.vectorx.spring5.s13_aspectj_annatation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = "com.vectorx.spring5.s13_aspectj_annatation")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
}
