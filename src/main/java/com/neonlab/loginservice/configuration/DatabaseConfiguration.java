package com.neonlab.loginservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfiguration {

    private final DatasourceConfig config;

    @Bean
    public DataSource dataSource(){
        var retVal = new DriverManagerDataSource();
        retVal.setDriverClassName(config.getDriver());
        retVal.setUrl(config.getUrl());
        retVal.setUsername(config.getUsername());
        retVal.setPassword(config.getPassword());
        return retVal;
    }


}
