package com.app.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class BookingServiceRoutes {

        @Value("${services.booking.url}")
        private String bookingServiceUrl;

        @Bean
        public RouterFunction<ServerResponse> bookingRoutes() {
                // Forward POST /api/v1/booking on gateway -> /api/v1/booking on booking service
                return GatewayRouterFunctions.route("booking-service")
                                .route(
                                                RequestPredicates.POST("/api/v1/booking"),
                                                // use the property and append path if needed
                                                HandlerFunctions.http(bookingServiceUrl + "/api/v1/booking"))
                                .filter(
                                                CircuitBreakerFilterFunctions.circuitBreaker(
                                                                "bookingServiceCircuitBreaker",
                                                                URI.create("forward:/fallbackRoute")))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> fallbackRoute() {
                return GatewayRouterFunctions.route("fallbackRoute")
                                .POST("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                                                .body("Booking service is down"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> bookingServiceApiDocs() {
                // IMPORTANT: use bookingServiceUrl, not "bookingServiceUrl"
                return GatewayRouterFunctions.route("booking-service-api-docs")
                                .route(
                                                RequestPredicates.path("/docs/bookingservice/v3/api-docs"),
                                                HandlerFunctions.http(bookingServiceUrl) // base URL of booking service
                                )
                                .filter(setPath("/v3/api-docs"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> bookingServiceSwaggerUi() {
                return GatewayRouterFunctions.route("booking-service-Api-docs")
                                .route(
                                                RequestPredicates.path("/docs/bookingservice/v3/api-docs"),
                                                HandlerFunctions.http(bookingServiceUrl) // base URL of booking service
                                )
                                .filter(setPath("/v3/api-docs"))
                                .build();
        }
}

/**
 * Functional route definitions used by the API gateway to forward requests to
 * the booking service.
 *
 * This class defines:
 * - a route that forwards POST booking requests to the booking service and
 *   wraps the call with a Resilience4j circuit breaker that forwards to a
 *   local fallback when the service is unavailable.
 * - helper routes that proxy the downstream booking service OpenAPI JSON
 *   so the gateway can expose the docs under `/docs/bookingservice/v3/api-docs`.
 *
 * The routing uses `GatewayRouterFunctions` and `HandlerFunctions.http(...)`
 * which cause the gateway to act as a simple HTTP forwarder to the downstream
 * service base URL configured in `services.booking.url`.
 */
