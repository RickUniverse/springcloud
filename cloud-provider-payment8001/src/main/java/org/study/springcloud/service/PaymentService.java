package org.study.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.study.springcloud.entities.Payment;

/**
 * @author lijichen
 * @date 2021/2/11 - 19:13
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}