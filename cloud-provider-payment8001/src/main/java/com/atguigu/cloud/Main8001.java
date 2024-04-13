package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.auguigu.cloud
 * @Project：cloud2024
 * @Date：2024/3/16 16:48
 * @description：
 */
@EnableDiscoveryClient // 开始consul检测
@RefreshScope // 开启自动刷新consul
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}
