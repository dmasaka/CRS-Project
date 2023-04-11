package com.incedo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories("com.incedo.repository")
@EntityScan("com.incedo.entity")
@ComponentScan({"com.incedo.*"})
@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
public class CrsIncedoSpringRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsIncedoSpringRestJpaApplication.class, args);
	}

}
