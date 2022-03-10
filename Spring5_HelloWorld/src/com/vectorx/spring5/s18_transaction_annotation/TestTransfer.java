package com.vectorx.spring5.s18_transaction_annotation;

import com.vectorx.spring5.s18_transaction_annotation.config.TxConfig;
import com.vectorx.spring5.s18_transaction_annotation.service.TransferRecordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestTransfer {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        TransferRecordService transferRecordService = context.getBean("transferRecordService", TransferRecordService.class);
        transferRecordService.transferAccounts(100, "Lucy", "Mary");
    }
}
