package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.controller
 * @Project：springcloud-project
 * @Date：2024/3/16 20:02
 * @description：
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    /**
     *根据id获取订单
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id) {
        // OpenFeign默认超时时间 60s
        ResultData resultData = null;
        try {
            log.info("=========调用开始===== {}", DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("=========调用结束===== {}", DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }
    @GetMapping("/feign/pay/get/info")
    public ResultData mylb() {
        return payFeignApi.mylb();
    }
}
