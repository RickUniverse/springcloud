package org.study.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:45
 */
@Configuration
@MapperScan({"org.study.springcloud.alibaba.dao"})
public class MyBatisConfig {

}