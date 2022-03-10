package com.vectorx.spring5.s18_transaction_annotation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 转账Dao实现
 */
@Repository
public class TransferRecordDaoImpl implements TransferRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void transferOut(int amount, String username) {
        String sql = "update t_account set amount=amount-? where username=?";
        Object[] args = {amount, username};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void transferIn(int amount, String username) {
        String sql = "update t_account set amount=amount+? where username=?";
        Object[] args = {amount, username};
        jdbcTemplate.update(sql, args);
    }
}
