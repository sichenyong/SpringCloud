package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud
 * @Project：springcloud-project
 * @Date：2024/3/24 10:25
 * @description：
 */
@SpringBootApplication
@EnableFeignClients // 支持远程调用
@EnableDiscoveryClient // 注册进 nacos
@MapperScan(basePackages = "com.atguigu.cloud.mapper")
public class Main2001 {
    public static void main(String[] args) {
        SpringApplication.run(Main2001.class, args);
    }
}
