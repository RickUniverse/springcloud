package org.study.springcloud.alibaba.service;

import org.springframework.stereotype.Component;
import org.study.springcloud.entities.CommonResult;
import org.study.springcloud.entities.Payment;

/**
 * @author lijichen
 * @date 2021/2/17 - 19:03
 */
@Component
public class PaymentFallbackService implements PaymentService{

    // 如果调用出错(provider下线)：new CommonResult<>(444,"服务降级返回，---PaymentFallbackService",new Payment(id,"ErrorSerial
    // 友好提示
    // 不是sentinel 降级，不用配置sentinel就会触发该方法
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回，---PaymentFallbackService",new Payment(id,"ErrorSerial"));
    }
}
