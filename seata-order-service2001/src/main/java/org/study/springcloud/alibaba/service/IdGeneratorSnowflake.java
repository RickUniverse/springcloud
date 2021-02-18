package org.study.springcloud.alibaba.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:50
 */
@Slf4j
@Component
public class IdGeneratorSnowflake {
    /**
     * 这两个值的范围都是 0 ~ 31
     * workerId = 0; 哪个机房
     * datacenterId = 1; 哪台电脑
     */
    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId); // 0号机房的，电脑 1

    /**
     * @PostConstruct 类构造完成后执行public void init() {} 方法
     */
    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}", workerId);
        } catch (Exception e) {
            log.info("当前机器的workerId获取失败", e);
            workerId = NetUtil.getLocalhostStr().hashCode();
            log.info("当前机器 workId:{}", workerId);
        }

    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        // 1240536286826201088
        System.out.println(new IdGeneratorSnowflake().snowflakeId());
    }
}
