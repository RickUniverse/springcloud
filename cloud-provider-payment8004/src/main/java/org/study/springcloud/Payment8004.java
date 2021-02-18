package org.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lijichen
 * @date 2021/2/12 - 19:43
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class Payment8004 {

    public static void main(String[] args) {
        SpringApplication.run(Payment8004.class,args);
    }
}