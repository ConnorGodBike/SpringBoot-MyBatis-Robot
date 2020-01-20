package com.springboot.robot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 开启rabbitMq
@EnableRabbit
// 开始事务
@EnableTransactionManagement(order = 100, proxyTargetClass = true)
// 扫描dao
@MapperScan(basePackages = {"com.springboot.robot.modules.**.dao"})
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
