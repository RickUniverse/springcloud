package org.study.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.springcloud.alibaba.domain.CommonResult;
import org.study.springcloud.alibaba.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:10
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }
}
