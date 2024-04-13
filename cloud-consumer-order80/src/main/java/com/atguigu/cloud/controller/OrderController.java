package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
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

//    public static final String PAYMENT_URL="http://localhost:8001"; // 先硬编码写死
    public static final String PAYMENT_URL="http://cloud-payment-service"; // 写服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/pay/add")
    public ResultData<Integer> addOrder(@RequestBody PayDTO payDTO) {
        log.info("添加的订单内容{}", payDTO);
        return restTemplate.postForObject(PAYMENT_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getOrderById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/"+id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    public ResultData getInfoByConsul() {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/info", ResultData.class);
    }

    @PutMapping(value = "/consumer/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {

        ResponseEntity<ResultData> responseEntity = restTemplate.exchange(PAYMENT_URL + "/pay/update", HttpMethod.PUT,
                new HttpEntity<>(payDTO), ResultData.class, payDTO);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("修改订单异常");
        }
    }

    @DeleteMapping(value = "/consumer/pay/delete/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        ResponseEntity<ResultData> responseEntity = restTemplate.exchange(PAYMENT_URL + "/pay/del/"+id, HttpMethod.DELETE,
                new HttpEntity<>(id), ResultData.class, id);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("修改订单异常");
        }
    }

    /**
     * 演示获取consul上所有的服务列表
     */
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
