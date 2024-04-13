package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.HashMap;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud
 * @Project：springcloud-project
 * @Date：2024/3/16 19:26
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main80 {
    public static void main(String[] args) {
        SpringApplication.run(Main80.class, args);

        HashMap<String, String> stringStringHashMap = new HashMap<>();

        stringStringHashMap.put("1", "2");
    }
}
