package org.study.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.study.springcloud.alibaba.domain.Order;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:48
 */
@Mapper
public interface OrderDao {
    /**
     * 1 新建订单
     * @param order
     * @return
     */
    int create(Order order);

    /**
     * 2 修改订单状态,从0改为1
     * @param userId
     * @param status
     * @return
     */
    int update(@Param("userId") Long userId, @Param("status") Integer status);
}
