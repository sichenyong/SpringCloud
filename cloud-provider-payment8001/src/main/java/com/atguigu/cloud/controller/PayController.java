package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.controller
 * @Project：cloud2024
 * @Date：2024/3/16 17:07
 * @description：
 */
@Tag(name = "支付微服务模块", description = "支付CRUD")
@RestController
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法， JSON串作为参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info("添加的内容{}", pay);
        int i = payService.add(pay);
        log.info("成功插入记录，返回值:{}", i);
        return ResultData.success("成功插入记录，返回值: " + i);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        log.info("成功删除记录，返回值:{}", i);
        return ResultData.success(i);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<Integer> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        log.info("修改的内容{}", payDTO);

        int i = payService.update(pay);
        log.info("成功更新记录，返回值:{}", i);
        return ResultData.success(i);

    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        if (id == -1) throw new RuntimeException("订单号不能为负数");
        Pay pay = payService.getById(id);
        if (pay == null) throw new NullPointerException("查询的记录为空");

        // 模拟超时,测试出Feign的默认调用超时时间
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("查询的内容{}", pay);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询所有流水",description = "查询所有支付支付流水方法")
    public ResultData<List<Pay>> getAll() {
        List<Pay> pays = payService.getAll();
        return ResultData.success(pays);
    }

    @Value("${server.port}")
    private String port;

    /**
     * demo: 获取consul上的配置信息
     * @param guiguInfo
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    public ResultData<String> getValueByConsul(@Value("${atguigu.info}") String guiguInfo) {

        return ResultData.success("guigu info: :" + guiguInfo + "\t" + "port:" + port);
    }
}
