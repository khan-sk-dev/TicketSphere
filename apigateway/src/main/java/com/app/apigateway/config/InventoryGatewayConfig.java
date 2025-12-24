/*
 * This file contains an example Route configuration for the inventory service.
 *
 * The implementation is intentionally commented out because the gateway uses
 * a functional RouterFunction-based approach in
 * `InventoryServiceRoutes.java`. The commented code demonstrates an
 * alternative `RouteLocator`-based configuration in case maintainers prefer
 * that style.
 */

// package com.app.apigateway.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class InventoryGatewayConfig {

//     // Example RouteLocator-based configuration (commented):
//     // Forwards `/inventory/**` requests to the configured inventory service
//     // and strips the first path segment before forwarding.
//     @Bean
//     public RouteLocator inventoryRoutes(RouteLocatorBuilder builder,
//         @Value("${services.inventory.url:http://localhost:8082}") String inventoryUrl) {
//         return builder.routes()
//             .route("inventory_route", r -> r.path("/inventory/**")
//                 .filters(f -> f.stripPrefix(1))
//                 .uri(inventoryUrl))
//             .build();
//     }

// }
