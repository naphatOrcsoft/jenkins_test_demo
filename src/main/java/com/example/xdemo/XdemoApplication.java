package com.example.xdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
// @EnableJpaRepositories("com.example.xdemo.repository")
// @EntityScan("com.example.xdemo.dto")
// @ActiveProfiles("test")
// @EnableJpaRepositories(basePackages = "com.example.xdemo.repository")
// @EntityScan(basePackages = {"com.example.xdemo.dto"})
public class XdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdemoApplication.class, args);
	}

}
