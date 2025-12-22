// package com.app.apigateway.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class InventoryGatewayConfig {

// @Bean
// public RouteLocator inventoryRoutes(RouteLocatorBuilder builder,
// @Value("${services.inventory.url:http://localhost:8082}") String
// inventoryUrl) {
// return builder.routes()
// .route("inventory_route", r -> r.path("/inventory/**")
// .filters(f -> f.stripPrefix(1))
// .uri(inventoryUrl))
// .build();
// }

// }
