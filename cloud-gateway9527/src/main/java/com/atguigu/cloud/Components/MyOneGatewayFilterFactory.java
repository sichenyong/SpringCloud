package com.atguigu.cloud.Components;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class MyOneGatewayFilterFactory extends AbstractGatewayFilterFactory<MyOneGatewayFilterFactory.Config> {

    public MyOneGatewayFilterFactory() {
        super(MyOneGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(MyOneGatewayFilterFactory.Config config) {

        return new GatewayFilter() {
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest req = exchange.getRequest();
                System.out.println("进入了自定义网关过滤器MyOneGatewayFilterFactory， status:" + config.getStatus());

                if (req.getQueryParams().containsKey("atguigu")) { //status==atguigu才算
                    return chain.filter(exchange);
                }
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                    return exchange.getResponse().setComplete(); //完成
                }
            }

        };
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("status");
    }

    static class Config {
        @NotNull
        private String status; //设定一个状态值

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
