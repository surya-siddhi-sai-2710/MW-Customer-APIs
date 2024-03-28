package com.dh.middleware.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:spring/camel-context.xml" })
@ComponentScan("com.dh.middleware.customer.*")
public class MidddlewareCustomerApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidddlewareCustomerApIsApplication.class, args);
	}

}
