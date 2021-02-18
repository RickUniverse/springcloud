package org.study.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author lijichen
 * @date 2021/2/14 - 20:04
 */
@Component// 记得加
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_Timeout,o(╥﹏╥)o";
    }
}
