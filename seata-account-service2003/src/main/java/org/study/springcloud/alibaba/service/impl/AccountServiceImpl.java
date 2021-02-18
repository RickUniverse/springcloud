package org.study.springcloud.alibaba.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.study.springcloud.alibaba.dao.AccountDao;
import org.study.springcloud.alibaba.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:13
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("----> account-service中扣减用户余额开始");

        // 因为有OpenFegin 调用的默认超时时间是1 秒， 20 秒一定会报错
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId,money);
        LOGGER.info("----> account-service中扣减用户余额开始");
    }
}
