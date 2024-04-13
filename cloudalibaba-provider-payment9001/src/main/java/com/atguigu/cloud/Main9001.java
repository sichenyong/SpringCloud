package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud
 * @Project：springcloud-project
 * @Date：2024/3/22 16:24
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Main9001
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main9001.class,args);
    }
}
