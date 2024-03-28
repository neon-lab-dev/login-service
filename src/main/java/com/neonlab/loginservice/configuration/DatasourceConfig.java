package com.neonlab.loginservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sqlite")
public class DatasourceConfig {

    private String driver;
    private String url;
    private String username;
    private String password;

}
