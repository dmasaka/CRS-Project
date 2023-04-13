package com.incedo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories("com.incedo.repository")
@EntityScan("com.incedo.entity")
@ComponentScan({"com.incedo.*"})
@EnableAutoConfiguration
@EnableWebMvc
@EnableDiscoveryClient
public class ProfessorMicroserviceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessorMicroserviceProducerApplication.class, args);
	}

}
