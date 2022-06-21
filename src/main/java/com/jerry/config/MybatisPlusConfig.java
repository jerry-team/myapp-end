package com.jerry.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jerry.mapper")
public class MybatisPlusConfig
{

}
