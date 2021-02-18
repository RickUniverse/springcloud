package org.study.springcloud.alibaba.service;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:01
 */
public interface StorageService {

    void decrease(Long productId, Integer count);

}
