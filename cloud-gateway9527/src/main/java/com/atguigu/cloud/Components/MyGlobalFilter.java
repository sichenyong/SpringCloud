package com.atguigu.cloud.Components;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    private static final String BEGIN_VISIT_TIME = "begin_visit_time"; // 开始调用方法的时间
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 先记录一下访问接口的开始时间
        Map<String, Object> attributes = exchange.getAttributes();

        attributes.put(BEGIN_VISIT_TIME, System.currentTimeMillis());

        // 允许进行下去
        return chain.filter(exchange)
                .then(Mono.fromRunnable(()->{ // filter回来的时候
            // 开启一个线程统计
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                System.out.println("访问接口地址：" + exchange.getRequest().getURI().getPath());
                System.out.println("访问接口时间：" + (System.currentTimeMillis() - beginVisitTime) + "毫秒");
            }
        }));
    }

    /**
     * 数字越小，优先级越高！
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
