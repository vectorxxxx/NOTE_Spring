package com.vectorx.spring5.s16_transaction;

import com.vectorx.spring5.s16_transaction.service.TransferRecordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTransfer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean14.xml");
        TransferRecordService transferRecordService = context.getBean("transferRecordService", TransferRecordService.class);
        transferRecordService.transferAccounts(100, "Lucy", "Mary");
    }
}
