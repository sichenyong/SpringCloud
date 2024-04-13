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
 * @Date：2024/3/24 10:58
 * @description：
 */
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper") //import
@EnableDiscoveryClient
@EnableFeignClients // 可以不加，在这里没有调用别的远程服务
public class Main2002 {
    public static void main(String[] args) {
        SpringApplication.run(Main2002.class, args);
    }
}
