package com.neonlab.loginservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//writtten for interakt may be we will modify later
public class PnoneNoConfig {

        @Value("${interakt.apiKey}")
        private String apiKey;

        @Value("${interakt.apiSecret}")
        private String apiSecret;




}
