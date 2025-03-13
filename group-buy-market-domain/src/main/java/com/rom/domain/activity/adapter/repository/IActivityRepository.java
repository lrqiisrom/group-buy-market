package com.rom.domain.activity.adapter.repository;


import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.model.valobj.SkuVO;

/**
 * @description 活动仓储(数据库查询)
 */
public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscount(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);
}
