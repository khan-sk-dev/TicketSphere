package com.app.leetjourney.inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}

/**
 * Inventory Service bootstrap class.
 *
 * Starts the Spring application that provides inventory queries and updates
 * for events and venues.
 */