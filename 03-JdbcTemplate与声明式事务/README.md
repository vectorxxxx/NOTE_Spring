> 
>
> 笔记来源：[尚硅谷Spring框架视频教程（spring5源码级讲解）](https://www.bilibili.com/video/BV1Vf4y127N5)

[TOC]

# JdbcTemplate与声明式事务

## 1、JdbcTemplate

### 1.1、概述

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

### 1.3、添加操作

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

### 1.4、修改和删除

修改、删除操作和添加操作代码逻辑基本一致

BookService 类：添加`updateBook()`和`deleteBook()`方法

```java
// 修改
public int updateBook(Book book) {
    return bookDao.update(book);
}
//删除
public int deleteBook(String id) {
    return bookDao.delete(id);
}
```

BookDao 类：添加`update()`和`delete()`方法

```java
// 修改
int update(Book book);
// 删除
int delete(String id);
```

BookDaoImpl 类：实现`update()`和`delete()`方法

```java
// 修改
@Override
public int update(Book book) {
    String sql = "update t_book set bname=?,bstatus=? where bid=?";
    Object[] args = {book.getBname(), book.getBstatus(), book.getBid()};
    return jdbcTemplate.update(sql, args);
}
// 删除
@Override
public int delete(String id) {
    String sql = "delete from t_book where bid=? ";
    return jdbcTemplate.update(sql, id);
}
```

测试修改

```java
//修改图书信息
Book book = new Book();
book.setBid("1");
book.setBname("JdbcTemplate");
book.setBstatus("update");
int result2 = bookService.updateBook(book);
System.out.println(result2);
```

测试结果

![image-20220307210646847](https://s2.loli.net/2022/03/07/TCLlJNIZ3RBovUX.png)

测试删除

```java
//删除图书
int result3 = bookService.deleteBook("1");
System.out.println(result3);
```

测试结果

![image-20220307211156970](https://s2.loli.net/2022/03/07/JPM6ABoLmV7ZeWj.png)

### 1.5、查询操作

这里演示三种查询操作：

- 1）查询返回某个值
- 2）查询返回对象
- 3）查询返回集合

为了演示效果，需要先在数据库的`t_book`表中添加两条数据

![image-20220307214231166](https://s2.loli.net/2022/03/07/oCSa3qAMGYbyfRN.png)

接着我们先将代码完成，最后再作进一步的分析说明

**代码实现**

BookService 类：添加`findCount()`、`findById()`和`findAll()`方法

```java
// 查找返回一个值
public int findCount() {
    return bookDao.selectCount();
}
// 查找返回对象
public Book findById(String id) {
    return bookDao.selectById(id);
}
// 查找返回集合
public List<Book> findAll() {
    return bookDao.selectAll();
}
```

BookDao 类：添加`selectCount()`、`selectById()`和`selectAll()`方法

```java
// 查找返回一个值
int selectCount();
// 查找返回对象
Book selectById(String id);
// 查找返回集合
List<Book> selectAll();
```

BookDaoImpl 类：实现`selectCount()`、`selectById()`和`selectAll()`方法

```java
// 查找返回一个值
@Override
public int selectCount() {
    String sql = "select count(0) from t_book";
    return jdbcTemplate.queryForObject(sql, Integer.class);
}
// 查找返回对象
@Override
public Book selectById(String id) {
    String sql = "select * from t_book where bid=?";
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
}
// 查找返回集合
@Override
public List<Book> selectAll() {
    String sql = "select * from t_book where 1=1";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
}
```

测试代码

```java
int count = bookService.findCount();
System.out.println(count);
Book book = bookService.findById("1");
System.out.println(book);
List<Book> bookList = bookService.findAll();
System.out.println(bookList);
```

测试结果

```java
2
Book{bid='1', bname='Spring', bstatus='add'}
[Book{bid='1', bname='Spring', bstatus='add'}, Book{bid='2', bname='SpringMVC', bstatus='add'}]
```

**代码分析**

上述代码逻辑中使用到了`queryForObject()`和`query()`方法

```java
jdbcTemplate.queryForObject(sql, Integer.class);
jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
```

分别对应`JdbcTemplate`中的三个方法

```java
public <T> T queryForObject(String sql, Class<T> requiredType);
public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args);
public <T> List<T> query(String sql, RowMapper<T> rowMapper);
```

其中，有两个参数值得关注，一个是`Class<T> requiredType`，另一个是`RowMapper<T> rowMapper`

- `Class<T> requiredType`：返回值的`Class`类型
- `RowMapper<T> rowMapper`：是一个接口，返回不同类型数据，可以使用其实现类进行数据的封装。其实现类有很多，因为我们需要返回一个数据库实体对象，所以可以选择使用`BeanPropertyRowMapper`

![image-20220307215452377](https://s2.loli.net/2022/03/07/YHjmQBSNXzDdPRL.png)

另外，`queryForObject(String sql, RowMapper<T> rowMapper, Object... args)`和`query(String sql, RowMapper<T> rowMapper)`的

区别在于

- `queryForObject`返回一个对象
- `query`返回一个集合

### 1.6、批量操作

`JdbcTemplate`中提供了`batchUpdate()`可供我们进行批量操作，如：批量添加、批量修改、批量删除等，代码实现上大同小异，我们对代码进行快速实现

**代码实现**

BookService 类：添加`batchAddBook()`、`batchUpdateBook()`和`batchDelBook()`方法

```java
// 批量添加
public void batchAddBook(List<Object[]> bookList) {
    bookDao.batchAdd(bookList);
}
// 批量修改
public void batchUpdateBook(List<Object[]> bookList) {
    bookDao.batchUpdate(bookList);
}
// 批量删除
public void batchDelBook(List<Object[]> bookList) {
    bookDao.batchDel(bookList);
}
```

BookDao 类：添加`batchAdd()`、`batchUpdate()`和`batchDel()`方法

```java
// 批量添加
void batchAdd(List<Object[]> bookList);
// 批量修改
void batchUpdate(List<Object[]> bookList);
// 批量删除
void batchDel(List<Object[]> bookList);
```

BookDaoImpl 类：实现`batchAdd()`、`batchUpdate()`和`batchDel()`方法

```java
// 批量添加
@Override
public void batchAdd(List<Object[]> bookList) {
    String sql = "insert into t_book(bid,bname,bstatus) values(?,?,?)";
    extractBatch(sql, bookList);
}
// 批量修改
@Override
public void batchUpdate(List<Object[]> bookList) {
    String sql = "update t_book set bname=?,bstatus=? where bid=?";
    extractBatch(sql, bookList);
}
// 批量删除
@Override
public void batchDel(List<Object[]> bookList) {
    String sql = "delete from t_book where bid=? ";
    extractBatch(sql, bookList);
}
private void extractBatch(String sql, List<Object[]> bookList,) {
    int[] ints = jdbcTemplate.batchUpdate(sql, bookList);
    System.out.println(ints);
}
```

**代码测试**

测试批量添加

```java
// 批量添加
List<Object[]> bookList = new ArrayList<>();
Object[] book1 = {"3", "Java", "batchAdd"};
Object[] book2 = {"4", "Python", "batchAdd"};
Object[] book3 = {"5", "C#", "batchAdd"};
bookList.add(book1);
bookList.add(book2);
bookList.add(book3);
bookService.batchAddBook(bookList);	
```

测试结果

![image-20220307223022287](https://s2.loli.net/2022/03/07/SgzfJ29Ph4VbpKl.png)

测试批量修改

```java
// 批量修改
List<Object[]> bookList = new ArrayList<>();
Object[] book1 = {"Java++", "batchUpdate", "3"};
Object[] book2 = {"Python++", "batchUpdate", "4"};
Object[] book3 = {"C#++", "batchUpdate", "5"};
bookList.add(book1);
bookList.add(book2);
bookList.add(book3);
bookService.batchUpdateBook(bookList);
```

测试结果

![image-20220307223232655](https://s2.loli.net/2022/03/07/Z8cquiezmorPxXY.png)

测试批量删除

```java
// 批量删除
List<Object[]> bookList = new ArrayList<>();
Object[] book1 = {"3"};
Object[] book2 = {"4"};
bookList.add(book1);
bookList.add(book2);
bookService.batchDelBook(bookList);
```

测试结果

![image-20220307223407753](https://s2.loli.net/2022/03/07/Vvtmo9BXnd1IfFG.png)

可以看出，上述测试都完全符合我们的预期

![img](https://s2.loli.net/2022/03/07/9Fu65poPBDlmryn.gif)

### 小结

简单总结下`JdbcTemplate`操作数据库的各个方法

- 添加、修改、删除操作：`update()`方法
- 查询操作：`queryForObject()`和`query()`方法，关注两个参数：
  - `Class<T> requiredType`：返回值的`Class`类型
  - `RowMapper<T> rowMapper`：接口，具体实现类`BeanPropertyRowMapper`，封装对象实体
- 批量操作：`batchUpdate()`方法

