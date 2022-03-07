package com.vectorx.spring5.s15_jdbctemplate.dao;

import com.vectorx.spring5.s15_jdbctemplate.entity.Book;

import java.util.List;

public interface BookDao {
    // 添加
    int add(Book book);

    // 修改
    int update(Book book);

    // 删除
    int delete(String id);

    // 查找返回一个值
    int selectCount();

    // 查找返回对象
    Book selectById(String id);

    // 查找返回集合
    List<Book> selectAll();

    // 批量添加
    void batchAdd(List<Object[]> bookList);

    // 批量修改
    void batchUpdate(List<Object[]> bookList);

    // 批量删除
    void batchDel(List<Object[]> bookList);
}
