package com.vectorx.spring5.s17_transaction_xml.service;

import com.vectorx.spring5.s17_transaction_xml.dao.TransferRecordDao;

public class TransferRecordService {
    private TransferRecordDao transferRecordDao;

    public void setTransferRecordDao(TransferRecordDao transferRecordDao) {
        this.transferRecordDao = transferRecordDao;
    }

    public void transferAccounts(int amount, String fromUser, String toUser) {
        transferRecordDao.transferOut(amount, fromUser);
        //模拟网络异常而导致操作中断
        int i = 10 / 0;
        transferRecordDao.transferIn(amount, toUser);
    }
}
