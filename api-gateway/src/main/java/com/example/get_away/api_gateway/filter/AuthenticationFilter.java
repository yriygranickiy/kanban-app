package com.example.get_away.api_gateway.filter;

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

import java.util.List;

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

        //TODO: нужно подумать как передавать id юзера в запрос для получения его permissions
        // call to /permissions on auth-service
        return authServiceWebClient.get()
                .uri("/api/permission")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                                    return clientResponse.createException().flatMap(Mono::error);
                })
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                 .flatMap(permissions -> {
                            ServerHttpRequest mutedRequest = request.mutate()
                            .header("X-User-Permissions", String.join(",", permissions))
                            .build();
                    return chain.filter(exchange.mutate().request(mutedRequest).build())
                            .doOnSuccess(v -> System.out.println("chain.filter() успешно завершился"))
                            .doOnError(e -> System.out.println("chain.filter() завершился ошибкой: " + e.getMessage()));

                })
                .onErrorResume(ex ->{
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                });
    }
}
