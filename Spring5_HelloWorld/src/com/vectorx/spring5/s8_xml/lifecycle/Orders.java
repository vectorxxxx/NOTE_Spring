package com.vectorx.spring5.s8_xml.lifecycle;

public class Orders {
    public Orders() {
        System.out.println("Step1.执行无参构造创建Bean实例.");
    }

    private String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("Step2.通过setter方法设置属性值.");
    }

    public void initMethod(){
        System.out.println("Step3.执行初始化方法.");
    }

    public void destoryMethod(){
        System.out.println("Step5.执行销毁方法.");
    }
}
