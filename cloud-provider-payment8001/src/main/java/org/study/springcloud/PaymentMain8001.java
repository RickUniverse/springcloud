package org.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * zookeeper地址：
 * @author lijichen
 * @date 2021/2/11 - 18:02
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient // 开启服务发现
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}