package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品查询
 */
@Mapper
public interface ISkuDao {
    Sku querySkuById(String skuId);
}
