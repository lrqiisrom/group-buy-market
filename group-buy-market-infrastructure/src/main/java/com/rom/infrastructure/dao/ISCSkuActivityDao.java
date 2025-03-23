package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 渠道商品活动配置关联表Dao
 * 主要用于SC+GoodsId查询拼团活动信息
 */
@Mapper
public interface ISCSkuActivityDao {
    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivityReq);
}
