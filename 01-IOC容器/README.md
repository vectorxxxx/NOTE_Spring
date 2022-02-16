> 笔记来源：[尚硅谷Spring框架视频教程（spring5源码级讲解）](https://www.bilibili.com/video/BV1Vf4y127N5)

[TOC]

# IOC 容器

## 1、概念和原理

### 1.1、IOC 是什么？

- 1）控制反转，把对象的创建和对象之间的调用过程，都交给 Spring 进行管理
- 2）使用 IOC 的目的：降低耦合性

### 1.2、IOC 底层实现

- 1）xml 解析
- 2）工厂模式
- 3）反射技术

### 1.3、图解 IOC 原理

**原始方法**

![image-20220215224645979](https://s2.loli.net/2022/02/15/tRs5qg1CokmNPTi.png)

代码示例

```java
public class UserDao {
    public void add(){
        // ...
    }
}
public class UserService {
    public void execute(){
        UserDao dao = new UserDao();
        dao.add();
    }
}
```

**工厂模式**

![image-20220215225031781](https://s2.loli.net/2022/02/15/YZSyzCNQiPM2LR6.png)

代码示例

```java
public class UserDao {
    public void add(){
        // ...
    }
}
public class UserFactory{
    public static UserDao getDao(){
        return new UserDao();
    }
}
public class UserService {
    public void execute(){
        UserDao dao = UserFactory.getDao();
        dao.add();
    }
}
```

**IOC 过程**

![image-20220215225920218](https://s2.loli.net/2022/02/15/5kRTry4CFhUDteX.png)

代码示例

```java
public class UserFactory{
	public static UserDao getDao(){
        // 1、xml解析
        String classValue = class属性值;
        // 2、通过反射创建对象
        Class clazz = Class.forName(classValue);
        return (UserDao)clazz.newInstance();
	}
}
```



## 2、IOC 接口

1）IOC 思想基于 IOC 容器完成，IOC 容器底层就是对象工厂

2）Spring 提供了IOC 容器实现的两种方式（即两个接口）

- `BeanFactory`
  - IOC 容器基本实现，是 Spring 的内部接口，不提供给开发人员使用
  - 加载配置文件时不会创建对象，使用对象时才会创建对象
- `ApplicationContext`
  - `BeanFactory`的子接口，提供更多功能，提供给开发人员使用
  - 加载配置文件就创建对象

### 2.1、ApplicationContext

`ApplicationContext`接口的实现类

![image-20220216202731825](https://s2.loli.net/2022/02/16/ivuGMy9IA18azFR.png)

- `FileSystemXmlApplicationContext`：xml 配置文件在系统盘中的文件全路径名

  ```java
  new FileSystemXmlApplicationContext("D:\workspace\NOTE_Spring\Spring5_HelloWorld\src\bean1.xml");
  ```

- `ClassPathXmlApplicationContext`：xml 配置文件在项目 src 下的相对路径名

  ```java
  new ClassPathXmlApplicationContext("bean1.xml");
  ```

### 2.2、BeanFactory

`BeanFactory`接口的子接口和实现类

![image-20220216203710031](https://s2.loli.net/2022/02/16/m9F862JWqAb5Zpc.png)

- `ConfigurableApplicationContext`：包含一些相关的扩展功能



## 3、IOC 操作 Bean 管理

### 3.1、Bean 管理是什么

Bean 管理指的是两个操作

- 1）Spring 创建对象
- 2）Spring 注入属性

```java
public class User{
    private String userName;

    public void setUserName(String userName){
        this.userName = userName;
    }
}
```

### 3.2、Bean 管理实现方式

- 1）基于 XML 配置文件方式实现
- 2）基于注解方式实现



## 4、IOC 操作 Bean 管理（基于 XML 方式）

### 4.1、基于 XML 方式创建对象

```xml
<!--配置User对象-->
<bean id="user" class="com.vectorx.spring5.User"></bean>
```

1）在 Spring 配置文件中，使用`bean`标签，标签里添加对应属性，就可以实现对象的创建

2）`bean`标签中有很多属性，介绍常用的属性

- `id`属性：唯一标识
- `class`属性：类全限定名、类全路径
- `name`属性：了解即可，早期为`Struts`框架服务，现已“退役”，作用跟`id`属性一样
- 其他属性：后面再做介绍...

3）创建对象时，默认执行无参构造方法

如果只提供一个有参构造方法，如下

```java
public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    // ...
}
```

仍然按照之前获取 User 对象创建方式，即

```java
// 1、加载自定义的Spring配置文件
ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
// 2、获取配置的User对象
User user = context.getBean("user", User.class);
```

则会报错

```java
警告: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'user' defined in class path resource [bean1.xml]: Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.vectorx.spring5.User]: No default constructor found; nested exception is java.lang.NoSuchMethodException: com.vectorx.spring5.User.<init>()

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'user' defined in class path resource [bean1.xml]: Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.vectorx.spring5.User]: No default constructor found; nested exception is java.lang.NoSuchMethodException: com.vectorx.spring5.User.<init>()

	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1334)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:582)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:953)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:918)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:85)
	at com.vectorx.spring5.testdemo.TestSpring5.testAdd(TestSpring5.java:13)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:69)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:221)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.vectorx.spring5.User]: No default constructor found; nested exception is java.lang.NoSuchMethodException: com.vectorx.spring5.User.<init>()
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:83)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1326)
	... 38 more
Caused by: java.lang.NoSuchMethodException: com.vectorx.spring5.User.<init>()
	at java.lang.Class.getConstructor0(Class.java:3082)
	at java.lang.Class.getDeclaredConstructor(Class.java:2178)
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:78)
	... 39 more
```

其中主要报错信息

```java
Failed to instantiate [com.vectorx.spring5.User]: No default constructor found
...
Caused by: java.lang.NoSuchMethodException: com.vectorx.spring5.User.<init>()
```

就是说：初始化 User 对象失败，因为没有找到默认构造，所以抛出了一个`NoSuchMethodException`异常，即 User 中没有`<init>()`方法

### 4.2、基于 XML 方式注入属性

`DI`：依赖注入，就是注入属性（但需要在创建对象基础上进行）

`IOC`和`DI`的区别：`DI`是`IOC`的一种具体实现

两种注入方式：Setter 方式、有参构造方法

- 第一种注入方式：通过 Setter 方式进行注入

  ```java
  public class Book{
      private String bname;
      
      // Setter 方法注入
      public void setBname(String bname){
          this.bname = bname;
      }
      
      public static void main(String[] args){
          Book book = new Book();
          book.setBname("book1");
      }
  }
  ```

- 第二种注入方式：通过有参构造方法进行注入

  ```java
  public class Book{
      private String bname;
      
      // 有参构造注入
      public Book(String bname){
          this.bname = bname;
      }
      
      public static void main(String[] args){
          Book book = new Book("book1");
      }
  }
  ```

#### 1）通过 Setter 方式注入

① 创建类，定义属性和 Setter 方法

```java
public class Book {
    private String bname;
    private String bauthor;

    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}

```

② 在 Spring 配置文件中配置对象创建，配置属性注入

```xml
<!-- 2、Setter方法注入属性 -->
<bean id="book" class="com.vectorx.spring5.s1_xml.setter.Book">
    <!-- 使用property完成属性注入
            name: 类中属性名称
            value: 向属性中注入的值
    -->
    <property name="bname" value="Spring实战 第5版"/>
    <property name="bauthor" value="克雷格·沃斯（Craig Walls）"/>
</bean>
```

#### 2）通过有参构造注入



