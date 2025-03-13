package com.rom.infrastructure.adapter.repository;

import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.model.valobj.SkuVO;

public class ActivityRepository implements IActivityRepository {
    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscount(String source, String channel) {
        return null;
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        return null;
    }
}
