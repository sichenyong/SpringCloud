package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud
 * @Project：springcloud-project
 * @Date：2024/3/23 13:28
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main8401 {

    public static void main(String[] args) {
        SpringApplication.run(Main8401.class, args);
    }
}
