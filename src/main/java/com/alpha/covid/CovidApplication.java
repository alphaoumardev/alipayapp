package com.alpha.covid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableSwagger2
@SpringBootApplication
@MapperScan("com.alpha.covid.mappers")
@EnableTransactionManagement
public class CovidApplication
{
    public static void main(String[] args) {SpringApplication.run(CovidApplication.class, args); }
}
