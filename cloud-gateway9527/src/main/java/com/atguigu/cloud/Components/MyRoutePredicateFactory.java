package com.atguigu.cloud.Components;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.Components
 * @Project：springcloud-project
 * @Date：2024/3/21 13:54
 * @description：
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public static final String USER_TYPE_KEY = "userType";

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    /**
     * 配置shotcut方式
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(USER_TYPE_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                String param = request.getQueryParams().getFirst(USER_TYPE_KEY);

                if (param == null) return false;
                if (param.equalsIgnoreCase(config.getUserType())) return true;
                else return false;
            }
        };
    }

    static class Config {
        @NotNull
        private String userType;

        public Config() {
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
