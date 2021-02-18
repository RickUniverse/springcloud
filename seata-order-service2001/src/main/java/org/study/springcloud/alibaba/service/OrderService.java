package org.study.springcloud.alibaba.service;

import org.study.springcloud.alibaba.domain.Order;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:51
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
