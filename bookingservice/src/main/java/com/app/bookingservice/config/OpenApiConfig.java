package com.app.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookingServiceApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Booking Service API")
                        .description("API documentation for the Booking Service")
                        .version("1.0.0"));
    }

}

/**
 * OpenAPI configuration for the Booking Service.
 *
 * Defines a minimal `OpenAPI` bean used by springdoc to generate API
 * documentation. Adjust title/description/version as needed for public
 * documentation or releases.
 */
