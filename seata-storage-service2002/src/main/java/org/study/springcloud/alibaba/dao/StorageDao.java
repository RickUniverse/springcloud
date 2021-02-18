package org.study.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:01
 */
@Mapper
public interface StorageDao {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
