package org.study.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目 加上限流，如果库存受不了了，订单限流
 * OpenFeign 有重试机制需要小心！！！！！！！！！！！！！！！！！
 *
 * 测试
 * http://localhost:2001/order/create?userId=1&productId=1&count=10&money=20
 * @author lijichen
 * @date 2021/2/18 - 16:43
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源的自动创建，使用SeaTa进行数据代理DataSourceProxyConfig
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderMain2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class,args);
    }
}