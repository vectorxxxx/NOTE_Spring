package com.vectorx.spring5.s18_transaction_annotation.service;

import com.vectorx.spring5.s18_transaction_annotation.dao.TransferRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.REPEATABLE_READ,
        timeout = 5,
        readOnly = false,
        noRollbackFor = ArithmeticException.class
)
public class TransferRecordService {
    @Autowired
    private TransferRecordDao transferRecordDao;

    public void transferAccounts(int amount, String fromUser, String toUser) {
        transferRecordDao.transferOut(amount, fromUser);
        //模拟网络异常而导致操作中断
        int i = 10 / 0;
        transferRecordDao.transferIn(amount, toUser);
    }
}
