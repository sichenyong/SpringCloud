package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.config
 * @Project：springcloud-project
 * @Date：2024/3/19 11:07
 * @description：
 */
@Configuration
public class FeignConfig {

//    @Bean
//    public Retryer retryer() {
//        // 最大请求次数为3（1（default） + 2）， 初始时间间隔为100ms，重试最大时间间隔为1s
//        return new Retryer.Default(100,1,3);
//    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
