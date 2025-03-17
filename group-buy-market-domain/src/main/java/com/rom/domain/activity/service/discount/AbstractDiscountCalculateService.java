package com.rom.domain.activity.service.discount;

import com.rom.domain.activity.model.valobj.DiscountTypeEnum;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * 折扣计算抽象类
 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        //人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
           boolean isCrowdRange =  filterTagId(userId, groupBuyDiscount.getTagId());
           if(!isCrowdRange) return originalPrice;
        }
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    private boolean filterTagId(String userId, String tagId){
        //过滤
        return true;
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
}


