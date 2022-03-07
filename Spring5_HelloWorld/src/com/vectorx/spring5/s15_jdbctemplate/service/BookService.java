package com.vectorx.spring5.s15_jdbctemplate.service;

import com.vectorx.spring5.s15_jdbctemplate.dao.BookDao;
import com.vectorx.spring5.s15_jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    // 添加
    public int addBook(Book book) {
        return bookDao.add(book);
    }

    // 修改
    public int updateBook(Book book) {
        return bookDao.update(book);
    }

    // 删除
    public int deleteBook(String id) {
        return bookDao.delete(id);
    }

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
}
