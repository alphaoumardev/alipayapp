package com.alpha.covid.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig
{
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor mp = new MybatisPlusInterceptor();
        PaginationInnerInterceptor pi = new PaginationInnerInterceptor();
        pi.setDbType(DbType.MYSQL);
        mp.addInnerInterceptor(pi);
        return mp;
    }
}
