package org.study.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.springcloud.alibaba.domain.CommonResult;

/**
 * @author lijichen
 * @date 2021/2/18 - 16:52
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}