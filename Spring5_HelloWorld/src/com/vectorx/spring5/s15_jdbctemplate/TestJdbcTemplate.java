package com.vectorx.spring5.s15_jdbctemplate;

import com.vectorx.spring5.s15_jdbctemplate.entity.Book;
import com.vectorx.spring5.s15_jdbctemplate.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJdbcTemplate {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean13.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        //模拟新增图书
        Book book = new Book();
        book.setBid("1");
        book.setBname("Spring JdbcTemplate");
        book.setBstatus("1");
        int result = bookService.addBook(book);
        System.out.println(result);
    }
}
