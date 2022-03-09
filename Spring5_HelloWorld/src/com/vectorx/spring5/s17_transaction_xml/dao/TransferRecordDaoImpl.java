package com.vectorx.spring5.s17_transaction_xml.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 转账Dao实现
 */
public class TransferRecordDaoImpl implements TransferRecordDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
