package com.vectorx.spring5.s17_transaction_xml.dao;

/**
 * 转账Dao
 */
public interface TransferRecordDao {
    void transferOut(int amount, String username);

    void transferIn(int amount, String username);
}
