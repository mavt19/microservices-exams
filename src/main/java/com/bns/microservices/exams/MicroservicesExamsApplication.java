package com.bns.microservices.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicesExamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesExamsApplication.class, args);
	}

}
