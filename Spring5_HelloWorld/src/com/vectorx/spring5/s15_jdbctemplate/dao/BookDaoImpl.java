package com.vectorx.spring5.s15_jdbctemplate.dao;

import com.vectorx.spring5.s15_jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加
    @Override
    public int add(Book book) {
        //操作JdbcTemplate对象，使用update方法进行添加操作
        String sql = "insert into t_book(bid,bname,bstatus) values(?,?,?)";
        Object[] args = {book.getBid(), book.getBname(), book.getBstatus()};
        return jdbcTemplate.update(sql, args);
    }

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

    private void extractBatch(String sql, List<Object[]> bookList) {
        int[] ints = jdbcTemplate.batchUpdate(sql, bookList);
        System.out.println(ints);
    }
}
