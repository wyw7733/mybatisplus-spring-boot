package com.baomidou.springboot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatasourceConfig {
	@Autowired
	private Environment environment;
	
	/**
	 * 配置数据源
	 * @return
	 */
	@Bean
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
        config.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        config.setAutoCommit(false);
        config.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        config.setUsername(environment.getProperty("spring.datasource.username"));
        config.setPassword(environment.getProperty("spring.datasource.password"));
        return new HikariDataSource(config);
	}
	/**
	 * 配置事务
	 * @param platformTransactionManager
	 * @return
	 */
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager){
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        // 事物管理器
        transactionInterceptor
                .setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();

        // 新增
        transactionAttributes.setProperty("insert*",
                "PROPAGATION_REQUIRED,-Throwable");
        // 修改

        transactionAttributes.setProperty("update*",
                "PROPAGATION_REQUIRED,-Throwable");
        // 删除
        transactionAttributes.setProperty("delete*",
                "PROPAGATION_REQUIRED,-Throwable");
        //查询
        transactionAttributes.setProperty("select*",
                "PROPAGATION_REQUIRED,-Throwable,readOnly");

        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        return transactionInterceptor;
	}
	
	//代理到ServiceImpl的Bean
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*ServiceImpl");
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }
}
