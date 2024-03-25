package com.neonlab.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.neonlab.common", "com.neonlab.loginservice"})
@EnableJpaRepositories(basePackages = {"com.neonlab.common.repositories"})
@EntityScan(basePackages = {"com.neonlab.common.entities"})
public class LoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServiceApplication.class, args);
	}
}
