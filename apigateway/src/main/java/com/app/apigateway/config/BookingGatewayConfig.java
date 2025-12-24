/*
 * This file contains an example Route configuration for the booking service.
 *
 * The implementation is currently commented out because this project uses the
 * Spring Cloud Gateway MVC "RouterFunction" approach implemented in
 * `BookingServiceRoutes.java` instead of defining a classic `RouteLocator`.
 *
 * Keep the code here as a reference for an alternate approach (route
 * definitions via `RouteLocatorBuilder`) and to help future maintainers
 * understand alternative configuration options.
 */

// package com.app.apigateway.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class BookingGatewayConfig {

//     // Example RouteLocator-based configuration (commented):
//     // It would forward requests with path `/booking/**` to the configured
//     // booking service URL and strip the first path segment before forwarding.
//     @Bean
//     public RouteLocator bookingRoutes(RouteLocatorBuilder builder,
//         @Value("${services.booking.url:http://localhost:8081}") String bookingUrl) {
//         return builder.routes()
//             .route("booking_route", r -> r.path("/booking/**")
//                 .filters(f -> f.stripPrefix(1))
//                 .uri(bookingUrl))
//             .build();
//     }

// }
