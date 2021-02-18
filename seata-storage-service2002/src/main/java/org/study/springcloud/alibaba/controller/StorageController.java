package org.study.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.springcloud.alibaba.domain.CommonResult;
import org.study.springcloud.alibaba.service.StorageService;

import javax.annotation.Resource;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:04
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功!");
    }
}
