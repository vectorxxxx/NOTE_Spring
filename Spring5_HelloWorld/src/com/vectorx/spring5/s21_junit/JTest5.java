package com.vectorx.spring5.s21_junit;

import com.vectorx.spring5.s18_transaction_annotation.service.TransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

////单元测试框架
//@ExtendWith(SpringExtension.class)
////加载配置文件
//@ContextConfiguration("classpath:bean15.xml")
//使用复合注解简化写法
@SpringJUnitConfig(locations = "classpath:bean15.xml")
public class JTest5 {
    @Autowired
    private TransferRecordService transferRecordService;

    @org.junit.jupiter.api.Test
    public void test() {
        transferRecordService.transferAccounts(100, "Lucy", "Mary");
    }
}
