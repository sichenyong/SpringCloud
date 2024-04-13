package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.service
 * @Project：cloud2024
 * @Date：2024/3/16 16:58
 * @description：
 */

public interface PayService {
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);

    Pay getById(Integer id);
    List<Pay> getAll();
}
