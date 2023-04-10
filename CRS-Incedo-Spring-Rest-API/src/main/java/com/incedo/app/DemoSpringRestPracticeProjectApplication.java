package com.incedo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.incedo.*"})
@EnableWebMvc
public class DemoSpringRestPracticeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringRestPracticeProjectApplication.class, args);
	}

}
