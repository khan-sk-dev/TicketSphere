package com.app.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SecurityConfig {

    @Value("${keycloak.auth.jwk-set-uri}")
    private String jwtSetUri;

    @Value("${security.excluded.urls}")
    private String[] excludeUrls;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(excludeUrls)
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwtSetUri).build();
    }

}

/**
 * Security configuration for the API gateway.
 *
 * This class configures the HTTP security filter chain used by the gateway.
 * Key points:
 * - `excludeUrls` are read from the `security.excluded.urls` property and are
 *   permitted without authentication (used for Swagger and public endpoints).
 * - OAuth2 Resource Server support is enabled and the JWT decoder is created
 *   from the Keycloak JWKS URL configured in `keycloak.auth.jwk-set-uri`.
 *
 * The `JwtDecoder` bean allows Spring Security to validate and parse incoming
 * JWT access tokens issued by the identity provider (Keycloak).
 */
