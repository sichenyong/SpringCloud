package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.service
 * @Project：springcloud-project
 * @Date：2024/3/24 10:37
 * @description：
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}