package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.entities
 * @Project：cloud2024
 * @Date：2024/3/16 16:56
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayDTO implements Serializable {
    private Integer id;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 订单流水号
     */
    private String orderNo;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 交易金额
     */
    private BigDecimal amount;
}
