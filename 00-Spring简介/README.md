> 笔记来源：[尚硅谷Spring框架视频教程（spring5源码级讲解）](https://www.bilibili.com/video/BV1Vf4y127N5)

[TOC]

# Spring 简介

## 1、Spring 课程内容介绍

- 1）Spring 概念
- 2）IOC 容器
- 3）AOP
- 4）JdbcTemplate
- 5）事务管理
- 6）Spring5 新特性



## 2、Spring 框架概述

Spring 是轻量级的开源的 J2EE 框架，可以解决企业应用开发的复杂性

Spring 有两个核心部分：IOC 和 AOP

- IOC：控制反转，把创建对象过程交给 Spring 进行管理
- AOP：面向切面，不修改源代码进行功能增强

Spring 特点

- 1）方便解耦，简化开发
- 2）支持 AOP 编程
- 3）方便程序测试
- 4）方便整合其他框架
- 5）方便进行事务操作
- 6）降低 API 开发难度

在课程中选取 Spring 版本 5.x 讲解



## 3、Spring 入门案例

### 1）下载 Spring5

- 查看 [Spring 官网](https://spring.io/projects/spring-framework#learn) 提供的 Spring 发布版本，这里使用 Spring 最新的稳定版本 5.3.15

  - GA（General Availability，普遍可用）为稳定版本
  - SNAPSHOT 为快照版本，不稳定

  ![image-20220213125236665](https://s2.loli.net/2022/02/13/5m9YgFlx13Ao8iv.png)

- 确定好需要的版本后，点右上角 [GitHub](https://github.com) 图标，进入下载地址：

  [https://github.com/spring-projects/spring-framework](https://github.com/spring-projects/spring-framework)

  ![image-20220213130023736](https://s2.loli.net/2022/02/13/K8nqdQfTiOFNzYv.png)

- 找到`Access to Binaries`，点击进入 [Spring Framework Artifacts](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Artifacts)

  ![image-20220213132027050](https://s2.loli.net/2022/02/13/lFp5mPB1bf7xqnR.png)

- 进入后，找到`Downloading a Distribution`，点击 [https://repo.spring.io](https://repo.spring.io/) 进入

  ![image-20220213132320304](https://s2.loli.net/2022/02/13/fcwrCiMuoJK5vVB.png)

- 左侧选择`Artifactory-Artifacts`，右侧选择`release-com-org-springframework-spring`

  ![image-20220213134123026](https://s2.loli.net/2022/02/13/hqlvGVMtSbisQIg.png)

- 复制右侧地址或直接点击打开：[https://repo.spring.io/ui/native/release/org/springframework/spring/](https://repo.spring.io/ui/native/release/org/springframework/spring/)，找到所需版本点击进入

  ![image-20220213134524409](https://s2.loli.net/2022/02/15/q3BHgDYStQAoeyF.png)

- 点击`Download Link`一栏链接，即可进行下载（网络问题，可能很慢）

  ![image-20220213134802702](https://s2.loli.net/2022/02/15/8QetzUSuZjcmNfA.png)

- 下载完毕，进行解压

  ![image-20220213141242805](https://s2.loli.net/2022/02/13/5cpW7L38oft6Usx.png)

### 2）创建普通 Java 工程

打开 IDEA 工具，点击`File-New-Project`

![image-20220213142138263](https://s2.loli.net/2022/02/13/TV13UCzXN4c7bD6.png)

选择`Java`，创建一个普通工程

![image-20220213142118708](https://s2.loli.net/2022/02/13/G3bXyqsfpeW5gIk.png)

勾选`Create project from template`

![image-20220213142259786](https://s2.loli.net/2022/02/13/lTPqLjV8vo7mbaA.png)

填写`Project name`、`Project location`和`Base package`

![image-20220213142427291](https://s2.loli.net/2022/02/13/xvEjRwneXd6hzKm.png)

### 3）导入 Spring5 相关 jar 包

通过下载解压的包中，提供了很多`jar`包，但并不需要所有都引入

- `*-5.3.15.jar`：编译包（正是我们需要的）
- `*-5.3.15-javadoc.jar`：文档包
- `*-5.3.15-sources.jar`：源码包

![image-20220213142631008](https://s2.loli.net/2022/02/13/nyemuRVPqIgD8st.png)

我们再看下 Spring5 模块

![image-20220213142535400](https://s2.loli.net/2022/02/13/HWxSqKj8u2Z9IPm.png)

其中的`Core Container`核心模块有

- `Beans`
- `Core`核心包
- `Context`上下文
- `Expression`表达式

![image-20220213143211949](https://s2.loli.net/2022/02/13/JYRpEDVvn9lK7IU.png)

我们目前导入这四个核心模块的包即可

- `spring-beans-5.3.15.jar`
- `spring-core-5.3.15.jar`
- `spring-context-5.3.15.jar`
- `spring-expression-5.3.15.jar`
- `commons-logging-1.2.jar`（不是 Spring 的包，但有依赖关系，不引入会报错）

在工程中新建一个`lib`文件夹，存放这些包

![image-20220213144049090](https://s2.loli.net/2022/02/13/R8kBeQixLnwVtSN.png)

将这些`jar`包导入项目中

![image-20220213144012507](https://s2.loli.net/2022/02/13/BmShCjbKxLgiNAs.png)

选中`lib`下的`jar`包

![image-20220213144144719](https://s2.loli.net/2022/02/13/fRr1VvDuqhTEoQt.png)

选中后效果，最后点击`OK`即可

![image-20220213144242158](https://s2.loli.net/2022/02/13/Zd43sLrEbSi7Phu.png)

### 4）创建普通类和方法

```java
public class User {
    public void add(){
        System.out.println("Hello World: User.add()方法");
    }
}
```

### 5）创建 Spring 配置文件，配置创建的对象

在`src`上点击`New-XML Configuration File-Spring Config`

![image-20220213144552041](https://s2.loli.net/2022/02/13/eXFV3oWwfG98OmM.png)

创建`xml`配置文件

![image-20220213144828139](https://s2.loli.net/2022/02/13/pJ16SrIb5i2ROse.png)

创建成功的`xml`文件已经有了基本的`<beans>`根标签

![image-20220213144922843](https://s2.loli.net/2022/02/13/2JfCZQhkcoejPFi.png)

接下来，配置相关对象的`<bean>`即可

```xml
<!--配置User对象-->
<bean id="user" class="com.vectorx.spring5.User"></bean>
```

### 6）进行测试代码的编写

```java
@Test
public void testAdd() {
    // 1、加载自定义的Spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

    // 2、获取配置的User对象
    User user = context.getBean("user", User.class);

    // 3、操作User对象
    System.out.println(user);
    user.add();
}
```

测试结果如下

![image-20220213145645855](https://s2.loli.net/2022/02/13/fGsc6CL7vlhWK48.png)