package com.vectorx.spring5.s16_transaction.service;

import com.vectorx.spring5.s16_transaction.dao.TransferRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferRecordService {
    @Autowired
    private TransferRecordDao transferRecordDao;

    public void transferAccounts(int amount, String fromUser, String toUser) {
        //try {
        // Step1、开启一个事务
        // Step2、进行业务逻辑实现
        transferRecordDao.transferOut(amount, fromUser);
        //模拟网络异常而导致操作中断
        int i = 10 / 0;
        transferRecordDao.transferIn(amount, toUser);
        // Step3、没有异常，则提交事务
        //} catch (Exception e) {
        // Step4、发生异常，则回滚事务
        //}
    }
}
