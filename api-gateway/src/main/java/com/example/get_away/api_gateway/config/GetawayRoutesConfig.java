package com.example.get_away.api_gateway.config;

import com.example.get_away.api_gateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetawayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route("auth-service-auth",r->r.path("/api/auth/**")
                        .uri("http://localhost:8082"))
                .route("auth-service-permission",r->r.path("/api/permission")
                        .uri("http://localhost:8082"))
                .route("task-service",r->r.path("/api/todo/**")
                        .filters(f-> f.filters(authenticationFilter))
                        .uri("http://localhost:8081"))
                .build();
    }
}
