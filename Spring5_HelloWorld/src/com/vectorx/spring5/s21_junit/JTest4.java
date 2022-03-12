package com.vectorx.spring5.s21_junit;

import com.vectorx.spring5.s18_transaction_annotation.service.TransferRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//单元测试框架
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件
@ContextConfiguration("classpath:bean15.xml")
public class JTest4 {
    @Autowired
    private TransferRecordService transferRecordService;

    @Test
    public void test() {
        transferRecordService.transferAccounts(100, "Lucy", "Mary");
    }
}
