package com.nerdysoft.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("menu-service", predicate ->
                        predicate.path("/pizzas/**")
                                .uri("lb://menu-service")
                )
                .route("auth-service", predicate ->
                        predicate.path("/login", "/sign-up","/.well-known/jwks.json", "/users/**")
                                .uri("lb://auth-service")
                )
                .route("order-service", predicate ->
                        predicate.path("/ingredients/**", "/orders/**")
                                .uri("lb://order-service"))
                .build();
    }
}
