package org.study.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lijichen
 * @date 2021/2/16 - 18:41
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 记得加
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
