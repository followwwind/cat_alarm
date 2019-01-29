package com.ancda.cat.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Title: CloudRunner
 * @Package com.ancda.palmbaby.ancda.com
 * @Description: 云监控服务
 * @author huanghy
 * @date 2018/8/16 15:34
 * @version V1.0
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = "com.ancda.cat.alarm")
@EnableCaching(proxyTargetClass = true)
@EnableScheduling
public class AlarmRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlarmRunner.class, args);
    }
}
