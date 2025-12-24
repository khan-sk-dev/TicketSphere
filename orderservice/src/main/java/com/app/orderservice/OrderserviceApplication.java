package com.app.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}

/**
 * Order Service application bootstrap.
 *
 * Starts the Spring application responsible for consuming booking events and
 * creating persistent orders, then delegating inventory updates to the
 * Inventory Service.
 */
