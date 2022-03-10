package com.vectorx.spring5.s18_transaction_annotation.dao;

/**
 * 转账Dao
 */
public interface TransferRecordDao {
    void transferOut(int amount, String username);

    void transferIn(int amount, String username);
}
