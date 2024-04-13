package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.service.impl
 * @Project：springcloud-project
 * @Date：2024/3/24 10:37
 * @description：
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource // 存储API
    private StorageFeignApi storageFeignApi;

    @Resource //账户API
    private AccountFeignApi accountFeignApi;


    /**
     * 创建订单方法
     * @param order：需要添加的订单信息
     */
    @Override
    // name：本次事务的名称
    @GlobalTransactional(name = "scy-create-order", rollbackFor = Exception.class) // 开启事务AT模式
    public void create(Order order) {
        // xid全局事务id检查，重要
        // RootContext: seata的全局上下文
        String xid = RootContext.getXID();
        // 创建订单
        log.info("-----------------------开始新建订单：\t" + "xid=" + xid);
        order.setStatus(0); // 标识正在创建
        int affectRows = orderMapper.insertSelective(order);

        Order orderFromDB = null;
        if (affectRows > 0) {
            // 再从数据库中查出来
            orderFromDB = orderMapper.selectOne(order);
            log.info("-----------------------新建订单成功：\t" + orderFromDB.toString());
            System.out.println();
            // 1. 减少库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), order.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            // 2. 减少金额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            // 3. 更新订单状态
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria()
                    .andEqualTo("id", orderFromDB.getId())
                    .andEqualTo("status", 0);

            int i = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("-------> 修改订单状态完成"+"\t"+i);
            log.info("-------> orderFromDB info: "+orderFromDB);
        }
        else {
            log.info("-----------------------新建订单失败");
        }
        log.info("-----------------------结束新建订单：\t" + "xid=" + xid);
    }
}
