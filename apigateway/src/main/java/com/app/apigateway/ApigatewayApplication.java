package com.app.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Main entry point for the API Gateway application.
 *
 * This class boots the Spring context for the gateway. The gateway itself
 * acts primarily as a reverse proxy/router for downstream services and
 * does not expose business endpoints directly (those live in the
 * individual microservices).
 */
public class ApigatewayApplication {

    /**
     * Standard Spring Boot main method that starts the application.
     *
     * @param args runtime arguments forwarded to SpringApplication
     */
    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

}