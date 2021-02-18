package org.study.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:12
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
