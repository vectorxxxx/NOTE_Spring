> 
>
> 笔记来源：[尚硅谷Spring框架视频教程（spring5源码级讲解）](https://www.bilibili.com/video/BV1Vf4y127N5)

[TOC]

# JdbcTemplate与声明式事务

## 1、JdbcTemplate

### 1.1、JdbcTemplate概述

前面我们已经学习了 Spring 中的`Core Container`核心部分和`AOP`、`Aspects`等面向切面编程部分，接下来就是`Data Access/Integration`即数据访问和集成部分

Spring 既可以单独使用，也可以集成其他框架，如`Hibernate`、`MyBatis`等。除此之外，其中对于`JDBC`也做了封装，即本章节的`JdbcTemplate`，用它可以比较方便地对数据库进行增删改查等操作

![image-20220306201355097](https://s2.loli.net/2022/03/06/NfepJE5nQI6jAd7.png)

总结一下：

- `JdbcTemplate`就是 Spring 框架对`JDBC`技术进行的二次封装模板，能够简化对数据库的操作

### 1.2、准备工作

**步骤预览**

- 1）引入相关`jar`包
- 2）Spring 配置文件配置`Druid`连接池信息
- 3）配置`JdbcTemplate`对象，注入`dataSource`
- 4）创建 Service 和 Dao 类，在 Dao 类中注入`JdbcTemplate`对象

**详细操作**

- 1）引入相关`jar`包（或依赖）
  - `druid`
  - `mysql-connector-java`
  - `spring-jdbc`
  - `spring-orm`
  - `spring-tx`

![image-20220306203931377](https://s2.loli.net/2022/03/06/gs6N9lbZckfdD4t.png)

- 2）Spring 配置文件配置`Druid`连接池信息

```xml
<context:property-placeholder location="classpath:jdbc.properties"/>
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${mysql.driverClassName}"/>
    <property name="url" value="${mysql.url}"/>
    <property name="username" value="${mysql.username}"/>
    <property name="password" value="${mysql.password}"/>
</bean>
```

沿用之前章节的`Jdbc.properties`配置信息，但稍作修改

```properties
mysql.driverClassName=com.mysql.jdbc.Driver
mysql.url=jdbc:mysql:///book_db
mysql.username=root
mysql.password=root
```

- 3）配置`JdbcTemplate`对象，注入`dataSource`

```xml
<!--配置JdbcTemplate-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!--属性注入dataSource-->
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

**为何使用属性注入？**

`JdbcTemplate`虽然含有`DataSource`的有参构造，但其调用了`setDataSource()`方法

![image-20220306213548608](https://s2.loli.net/2022/03/06/8e5aPG6Sx3YlZty.png)

这个方法是在其父类中定义了的

![image-20220306213626024](https://s2.loli.net/2022/03/06/n4qpwel6KRakdAm.png)

- 4）创建 Service 和 Dao 类，在 Dao 类中注入`JdbcTemplate`对象

Dao 类

```java
public interface BookDao {
}
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
```

Service 类

```java
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
}
```

别忘了开启注解扫描

```xml
<!--开启注解扫描-->
<context:component-scan base-package="com.vectorx.spring5.s15_jdbctemplate"/>
```

**配置文件整体结构**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--开启注解扫描-->
    <context:component-scan base-package="com.vectorx.spring5.s15_jdbctemplate"/>

    <!--配置dataSource-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${mysql.driverClassName}"/>
        <property name="url" value="${mysql.url}"/>
        <property name="username" value="${mysql.username}"/>
        <property name="password" value="${mysql.password}"/>
    </bean>

    <!--配置JdbcTemplate-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <!--属性注入dataSource-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```

### 1.3、JdbcTemplate操作数据库（添加）

**步骤预览**

- 1）创建数据库中`t_book`表对应的实体对象
- 2）编写 Service 和 Dao 代码，增加*添加图书*的功能逻辑
- 3）代码测试

**详细操作**

- 1）创建数据库中`t_book`表对应的实体对象

```java

public class Book {
    private String bid;
    private String bname;
    private String bstatus;

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBstatus() {
        return bstatus;
    }
    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }
}
```

- 2）编写 Service 和 Dao 代码，增加*添加图书*的功能逻辑

Service 类：添加`addBook()`方法

```java
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public int addBook(Book book) {
        return bookDao.add(book);
    }
}
```

Dao 类：通过操作`JdbcTemplate`对象的`update()`方法可实现插入，其中两个参数分别是

- 第一个参数`sql`：编写插入数据对应的`sql`语句，可使用通配符`?`做占位符
- 第二个参数`args`：可变参数列表，设置占位符对应的参数值

```java
public interface BookDao {
    int add(Book book);
}
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Book book) {
        //操作JdbcTemplate对象，使用update方法进行添加操作
        String sql = "insert into t_book(bid,bname,bstatus) values(?,?,?)";
        Object[] args = {book.getBid(), book.getBname(), book.getBstatus()};
        return jdbcTemplate.update(sql, args);
    }
}
```

- 3）代码测试

```java
ApplicationContext context = new ClassPathXmlApplicationContext("bean13.xml");
BookService bookService = context.getBean("bookService", BookService.class);

//模拟新增图书
Book book = new Book();
book.setBid("1");
book.setBname("Spring JdbcTemplate");
book.setBstatus("1");
int result = bookService.addBook(book);
System.out.println(result);
```

测试结果

```java
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
三月 06, 2022 10:25:49 下午 com.alibaba.druid.pool.DruidDataSource info
信息: {dataSource-1} inited
1
```

刷新数据库中`t_book`表数据，核验是否插入成功

![image-20220306222833478](https://s2.loli.net/2022/03/06/lr6szWvfcaO1bDm.png)

可以看到，表中成功新增了一条数据