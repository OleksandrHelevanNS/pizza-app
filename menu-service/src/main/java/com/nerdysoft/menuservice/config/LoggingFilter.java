package com.nerdysoft.menuservice.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements WebFilter {

    @Override
    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange,
                             WebFilterChain chain) {
        System.out.println("Incoming request: " + exchange.getRequest().getMethod() +
                " " + exchange.getRequest().getURI());

        exchange.getRequest().getQueryParams()
                .forEach((key, value) -> System.out.println(key + " = " + value));

        exchange.getRequest().getHeaders()
                .forEach((key, value) -> System.out.println(key + " : " + value));

        return chain.filter(exchange);
    }
}
