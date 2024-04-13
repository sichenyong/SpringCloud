package com.atguigu.cloud;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud
 * @Project：springcloud-project
 * @Date：2024/3/19 9:48
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient //使用consul注册服务
@EnableFeignClients //开启激活OpenFeign功能
public class MainFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeign80.class, args);
    }
}
