package com.app.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class InventoryServiceRoutes {

        @Value("${services.inventory.url}")
        private String inventoryServiceUrl;

        @Bean
        public RouterFunction<ServerResponse> inventoryRoutes() {
                return GatewayRouterFunctions.route("inventory-service")

                                // venue inventory
                                .route(RequestPredicates.GET("/api/v1/inventory/venue/{venueId}"),
                                                request -> forwardWithPathVariable(request, "venueId",
                                                                inventoryServiceUrl + "/api/v1/inventory/venue/"))

                                // event inventory
                                .route(RequestPredicates.GET("/api/v1/inventory/event/{eventId}"),
                                                request -> forwardWithPathVariable(request, "eventId",
                                                                inventoryServiceUrl + "/api/v1/inventory/event/"))

                                .build();
        }

        private static ServerResponse forwardWithPathVariable(ServerRequest request, String pathVariable,
                        String baseUrl) throws Exception {
                String value = request.pathVariable(pathVariable);
                return HandlerFunctions.http(baseUrl + value).handle(request);
        }

        @Bean
        public RouterFunction<ServerResponse> inventoryServiceApiDocs() {
                return GatewayRouterFunctions.route("inventory-service-api-docs")
                                .route(RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                                                HandlerFunctions.http(inventoryServiceUrl))
                                .filter(setPath("/v3/api-docs"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> inventoryServiceSwaggerUi() {
                return GatewayRouterFunctions.route("inventory-service-api-docs")
                                .route(RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                                                HandlerFunctions.http(inventoryServiceUrl))
                                .filter(setPath("/v3/api-docs"))
                                .build();
        }

}

/**
 * Functional route definitions for forwarding inventory-related requests to
 * the downstream inventory service.
 *
 * This class exposes:
 * - routes that forward GET requests for venue and event inventory to the
 *   inventory service by extracting the path variables and performing an HTTP
 *   forward to the configured service URL.
 * - routes that proxy the downstream inventory service OpenAPI JSON so the
 *   gateway can expose the docs at `/docs/inventoryservice/v3/api-docs`.
 *
 * The `forwardWithPathVariable` helper extracts the variable from the
 * incoming request and forwards to the target service path.
 */
