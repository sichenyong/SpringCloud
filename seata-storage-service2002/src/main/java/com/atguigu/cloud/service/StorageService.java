package com.atguigu.cloud.service;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.service
 * @Project：springcloud-project
 * @Date：2024/3/24 11:01
 * @description：
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}