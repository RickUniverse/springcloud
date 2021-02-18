package org.study.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MySelfRule 类必须在 OrderMain80 之外的包，否则会被所有服务共享，达不到定制的目的
 * @author lijichen
 * @date 2021/2/13 - 16:26
 */
@Configuration
public class MySelfRule {

    /**
     * 设置为随机负载均衡
     * @return
     */
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
