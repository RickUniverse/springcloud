package org.study.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lijichen
 * @date 2021/2/12 - 14:38
 */
@Configuration
public class ApplicationContentConfig {

    @Bean
    // 给restTemplet提供负载均衡能力
//    @LoadBalanced 注释到测试自定义的 策略 自检锁
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
