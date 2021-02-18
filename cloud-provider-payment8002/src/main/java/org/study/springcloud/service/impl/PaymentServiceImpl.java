package org.study.springcloud.service.impl;

import org.springframework.stereotype.Service;
import org.study.springcloud.dao.PaymentDao;
import org.study.springcloud.entities.Payment;
import org.study.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author lijichen
 * @date 2021/2/11 - 19:13
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}