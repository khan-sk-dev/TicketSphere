package com.app.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Booking Service application bootstrap.
 *
 * This class starts the Spring context for the booking microservice which
 * exposes booking-related REST endpoints, produces booking events and interacts
 * with other services (inventory, orders) as part of the booking flow.
 */
public class BookingserviceApplication {

	/**
	 * Application entry point.
	 *
	 * @param args runtime arguments forwarded to SpringApplication
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookingserviceApplication.class, args);
	}

}
