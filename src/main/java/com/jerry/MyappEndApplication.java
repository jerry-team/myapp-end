package com.jerry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.jerry.myappend.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MyappEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappEndApplication.class, args);
    }

}
