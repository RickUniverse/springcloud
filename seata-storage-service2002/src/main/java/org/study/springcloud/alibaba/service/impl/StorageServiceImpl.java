package org.study.springcloud.alibaba.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.study.springcloud.alibaba.dao.StorageDao;
import org.study.springcloud.alibaba.service.StorageService;

import javax.annotation.Resource;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:03
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("----> storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("----> storage-service中扣减库存结束");
    }
}
