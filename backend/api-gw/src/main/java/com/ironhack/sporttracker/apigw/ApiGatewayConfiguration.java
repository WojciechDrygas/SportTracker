package com.ironhack.sporttracker.apigw;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApiGatewayConfiguration {
    private final String AUTH_SERVICE = "AUTH-SERVICE";
    private final Environment environment;

    public ApiGatewayConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/users")
                        .uri("lb://" + AUTH_SERVICE))
                .route(p -> p.path("/user_details")
                        .filters(f -> f.filter(new AuthFilter(environment).apply(new AuthFilter.Config())))
                        .uri("lb://" + AUTH_SERVICE))
                .route(p -> p.path("/auth/login")
                        .uri("lb://" + AUTH_SERVICE))
                .build();
    }
}
