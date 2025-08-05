package com.example.get_away.api_gateway.filter;

import com.example.get_away.api_gateway.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {

    private final WebClient authServiceWebClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);


        if  (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return authServiceWebClient.get()
                .uri("/api/permission_and_id")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                                    return clientResponse.createException().flatMap(Mono::error);
                })
                .bodyToMono(new ParameterizedTypeReference<UserInfo>() {})
                 .flatMap(userInfo -> {
                            ServerHttpRequest mutedRequest = request.mutate()
                            .header("X-User-Id", userInfo.user_id().toString())
                            .header("X-User-Email", userInfo.email())
                            .header("X-User-Permissions", String.join(",", userInfo.permission()))
                            .build();
                            return chain.filter(exchange.mutate().request(mutedRequest).build());
                })
                .onErrorResume(ex ->{
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                });
    }
}
