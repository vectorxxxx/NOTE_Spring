# NOTE_Spring
## 介绍

:sparkles: 尚硅谷Spring框架视频教程（spring5源码级讲解）学习笔记



## 更新

- :link: Github：[vectorxxxx/NOTE_Spring: 尚硅谷Spring框架视频教程（spring5源码级讲解）学习笔记 (github.com)](https://github.com/vectorxxxx/NOTE_Spring)
- :link: Gitee：[NOTE_Spring: 尚硅谷Spring框架视频教程（spring5源码级讲解）学习笔记 (gitee.com)](https://gitee.com/vectorx/NOTE_Spring)
- :link: GitCode：[VectorUx / NOTE_Spring · GitCode](https://gitcode.net/qq_35925558/NOTE_Spring)
- :link: 语雀：[Spring从入门到精通 · 语雀 (yuque.com)](https://www.yuque.com/u21195183/vvllih)
- :link: 博客园：[Spring从入门到精通 - 随笔分类 - VectorX - 博客园 (cnblogs.com)](https://www.cnblogs.com/vectorx/category/2106560.html)
- :link: CSDN：[Spring从入门到精通_VectorX's Blog-CSDN博客](https://blog.csdn.net/qq_35925558/category_11631101.html?spm=1001.2014.3001.5482)
- :link: 掘金：[Spring从入门到精通 - VectorX的专栏 - 掘金 (juejin.cn)](https://juejin.cn/column/7064042024663515143)

<mark>**整理不易，还望各位看官一键三连 :heart: :heart: :heart: **</mark>

<mark>**整理不易，还望各位看官一键三连 :heart: :heart: :heart: **</mark>

<mark>**整理不易，还望各位看官一键三连 :heart: :heart: :heart: **</mark>

:sparkles:下面开始吧~

---

## 1、官网

- Spring官网：[Spring Framework](https://spring.io/projects/spring-framework#learn)
- Github源码：[GitHub - spring-projects/spring-framework: Spring Framework](https://github.com/spring-projects/spring-framework)
- Spring下载路径：[JFrog (spring.io)](https://repo.spring.io/ui/native/release/org/springframework/spring/)



## 2、Spring5模块

![image-20220213142535400](https://s2.loli.net/2022/03/12/ZdO7zvjgIDBi62o.png)

笔记主要内容目前涵盖（2022-03-12）

- 核心容器：`Beans`、`Core`、`Context`、`Expression`
- 数据访问集成：`JDBC`、`ORM`、`Transactions`
- 其他：`AOP`、`Aspects`、`Test`



## 3、相关依赖

核心

- `spring-beans`
- `spring-core`
- `spring-context`
- `spring-expression`
- `commons-logging`

AOP 相关

- `spring-aop`
- `aspectjrt`
- `aspectjweaver`

JdbcTemplate 相关

- `druid`
- `mysql-connector-java`
- `spring-jdbc`
- `spring-orm`
- `spring-tx`

日志相关

- `log4j-api`
- `log4j-core`
- `log4j-slf4j-impl`
- `slf4j-api`

Junit 相关

- `spring-test`

```xml
<!--核心-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-beans</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-expression</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
<!--AOP 相关-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.9.8</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.8</version>
    <scope>runtime</scope>
</dependency>
<!--JdbcTemplate 相关-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.3.16</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>5.3.16</version>
</dependency>
<!--日志相关-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.17.2</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.17.2</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.17.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.36</version>
</dependency>
<!--Junit 相关-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.3.16</version>
    <scope>test</scope>
</dependency>
```



## 4、导图

### 00-Spring简介

![00-Spring 简介](https://s2.loli.net/2022/03/12/qIDrfNmRXCWAEy3.png)

### 01-IOC容器

![01-IOC 容器](https://s2.loli.net/2022/03/12/z1vBP9tDoQSZrXn.png)

### 02-AOP

![02-AOP](https://s2.loli.net/2022/03/12/DXfhQzgBW2jal8L.png)

### 03-JdbcTemplate与声明式事务

![03-JdbcTemplate与声明式事务](https://s2.loli.net/2022/03/12/F6Ab9jzGw3XvCpq.png)

### 04-Spring5新功能

![04-Spring5新功能](https://s2.loli.net/2022/03/12/DgPXpqI793WmtMr.png)





整理难免有误，欢迎大家批评指正！

---

> 署名 4.0 国际 (CC BY 4.0)。您可以自由地：共享 — 在任何媒介以任何形式复制、发行本作品；演绎 — 修改、转换或以本作品为基础进行创作；在任何用途下，甚至商业目的。只要你遵守许可协议条款，许可人就无法收回你的这些权利。惟须遵守下列条件：署名 — 您必须给出适当的署名，提供指向本许可协议的链接，同时标明是否（对原始作品）作了修改。您可以用任何合理的方式来署名，但是不得以任何方式暗示许可人为您或您的使用背书。
