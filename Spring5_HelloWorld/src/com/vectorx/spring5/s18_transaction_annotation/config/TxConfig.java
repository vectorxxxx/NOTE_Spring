package com.vectorx.spring5.s18_transaction_annotation.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// 表名此类为配置类
@Configuration
// 自动扫描包
@ComponentScan(basePackages = "com.vectorx.spring5.s18_transaction_annotation")
// 开启事务
@EnableTransactionManagement
// 读取外部配置文件
@PropertySource(value = {"classpath:jdbc.properties"})
public class TxConfig {
    @Value(value = "${mysql.driverClassName}")
    private String driverClassName;
    @Value(value = "${mysql.url}")
    private String url;
    @Value(value = "${mysql.username}")
    private String username;
    @Value(value = "${mysql.password}")
    private String password;

    //配置dataSource
    @Bean
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //配置JdbcTemplate
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        //IOC容器会根据类型找到对应DataSource
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    //配置事务管理器
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
