package org.study.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.springcloud.alibaba.domain.CommonResult;
import org.study.springcloud.alibaba.domain.Order;
import org.study.springcloud.alibaba.service.IdGeneratorSnowflake;
import org.study.springcloud.alibaba.service.OrderService;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:47
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

    /**
     * 生成id,通过雪花算法
     *
     * @return
     */
    @GetMapping("/snowflake")
    public String getIDBySnowflake() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            // 直接提交了 20 个任务给 5 个线程处理
            threadPool.submit(() -> {
                System.out.println(idGeneratorSnowflake.snowflakeId());
            });
        }
        threadPool.shutdown();
        return "hello snowflake";
    }
}
