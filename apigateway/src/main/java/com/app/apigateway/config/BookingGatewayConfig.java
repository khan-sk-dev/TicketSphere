package com.app.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingGatewayConfig {

    @Bean
    public RouteLocator bookingRoutes(RouteLocatorBuilder builder,
            @Value("${services.booking.url:http://localhost:8081}") String bookingUrl) {
        return builder.routes()
                .route("booking_route", r -> r.path("/booking/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(bookingUrl))
                .build();
    }

}
