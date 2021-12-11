package com.ironhack.sporttracker.apigw;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApiGatewayConfiguration {
    private final String AUTH_SERVICE = "AUTH-SERVICE";
    private final String SPORT_DATA_SERVICE = "SPORT-DATA-SERVICE";
    private final String FAVORITE_SERVICE = "FAVORITE-SERVICE";
    private final String STATISTICAL_SERVICE = "STATISTICAL-SERVICE";
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
                .route(p->p.path("/leagues")
                        .uri("lb://"+SPORT_DATA_SERVICE))
                .route(p->p.path("/leagues/**")
                        .uri("lb://"+SPORT_DATA_SERVICE))
                .route(p->p.path("/teams/**")
                        .uri("lb://"+SPORT_DATA_SERVICE))
                .route(p -> p.path("/favorite_team")
                        .filters(f -> f.filter(new AuthFilter(environment).apply(new AuthFilter.Config())))
                        .uri("lb://" + FAVORITE_SERVICE))
                .route(p -> p.path("/delete/favorite/**")
                        .filters(f -> f.filter(new AuthFilter(environment).apply(new AuthFilter.Config())))
                        .uri("lb://" + FAVORITE_SERVICE))
                .route(p->p.path("/stats/football/**")
                        .uri("lb://"+STATISTICAL_SERVICE))
                .build();
    }
}
