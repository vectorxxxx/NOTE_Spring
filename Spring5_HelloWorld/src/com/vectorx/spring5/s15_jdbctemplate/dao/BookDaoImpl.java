package com.vectorx.spring5.s15_jdbctemplate.dao;

import com.vectorx.spring5.s15_jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
