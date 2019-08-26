package com.zzx.executor.config;



import com.zzx.executor.core.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(value = "com.zzx.executor")
public class LifeCycleConfiguration {
    @Autowired
    private DataSourceProperties properties;
    @Autowired
    private ExecutorService threadPool;
    @Autowired
    private UI ui;


    @Bean
    public RestTemplate restTemplate(){

        //TODO 生产环境数据库

//ykf
//        properties.setUrl("jdbc:mysql://rm-wz9c455xg8d2g2d917o.mysql.rds.aliyuncs.com:3306/ykf_bank?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
//        properties.setUsername("boot");
//        properties.setPassword("ykf@2019815");

        properties.setUrl("jdbc:mysql://120.25.106.200:3306/taobao?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
        properties.setUsername("root");
        properties.setPassword("root");

        //TODO mysql驱动
        properties.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //TODO Other Properties
        return new RestTemplate();
    }

    @Bean
    public ExecutorService getThreadPool(){
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    @Order(222222222)
    public void initial() {
        threadPool.submit(ui);
    }
}
