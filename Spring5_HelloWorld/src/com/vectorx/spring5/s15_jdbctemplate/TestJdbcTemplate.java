package com.vectorx.spring5.s15_jdbctemplate;

import com.vectorx.spring5.s15_jdbctemplate.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestJdbcTemplate {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean13.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        //新增图书
        //Book book = new Book();
        //book.setBid("1");
        //book.setBname("Spring JdbcTemplate");
        //book.setBstatus("1");
        //int result = bookService.addBook(book);
        //System.out.println(result);

        //修改图书
        //Book book = new Book();
        //book.setBid("1");
        //book.setBname("JdbcTemplate");
        //book.setBstatus("update");
        //int result2 = bookService.updateBook(book);
        //System.out.println(result2);

        //删除图书
        //int result3 = bookService.deleteBook("1");
        //System.out.println(result3);

        //查询图书
        //int count = bookService.findCount();
        //System.out.println(count);
        //Book book = bookService.findById("1");
        //System.out.println(book);
        //List<Book> bookList = bookService.findAll();
        //System.out.println(bookList);
        // 2
        // Book{bid='1', bname='Spring', bstatus='add'}
        // [Book{bid='1', bname='Spring', bstatus='add'}, Book{bid='2', bname='SpringMVC', bstatus='add'}]

        // 批量添加
        //List<Object[]> bookList = new ArrayList<>();
        //Object[] book1 = {"3", "Java", "batchAdd"};
        //Object[] book2 = {"4", "Python", "batchAdd"};
        //Object[] book3 = {"5", "C#", "batchAdd"};
        //bookList.add(book1);
        //bookList.add(book2);
        //bookList.add(book3);
        //bookService.batchAddBook(bookList);

        // 批量修改
        //List<Object[]> bookList = new ArrayList<>();
        //Object[] book1 = {"Java++", "batchUpdate", "3"};
        //Object[] book2 = {"Python++", "batchUpdate", "4"};
        //Object[] book3 = {"C#++", "batchUpdate", "5"};
        //bookList.add(book1);
        //bookList.add(book2);
        //bookList.add(book3);
        //bookService.batchUpdateBook(bookList);

        // 批量删除
        List<Object[]> bookList = new ArrayList<>();
        Object[] book1 = {"3"};
        Object[] book2 = {"4"};
        bookList.add(book1);
        bookList.add(book2);
        bookService.batchDelBook(bookList);
    }
}
