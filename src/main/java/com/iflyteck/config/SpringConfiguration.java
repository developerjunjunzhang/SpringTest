package com.iflyteck.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 *
 */
@Configuration // 标明是一个配置类
@ComponentScan("com.iflyteck") // 开启包扫描
// @EnableAspectJAutoProxy // 开启注解aop的支持
@EnableTransactionManagement
@Import({JdbcConfig.class,TransactionConfig.class}) // 导入配置类
@PropertySource("classpath:jdbcConfig.properties") // 引入配置文件
public class SpringConfiguration {
}
