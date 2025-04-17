package com.rom.domain.activity.service.discount;

import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.valobj.DiscountTypeEnum;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 折扣计算抽象类
 */
@Slf4j
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    @Resource
    protected IActivityRepository activityRepository;
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        //人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
           boolean isCrowdRange =  filterTagId(userId, groupBuyDiscount.getTagId());
           if(!isCrowdRange) {
               log.info("折扣优惠计算拦截，用户不在优惠人群标签范围内 userId:{}", userId);
               return originalPrice;
           }
        }
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    private boolean filterTagId(String userId, String tagId){
        //过滤
        return activityRepository.isTagCrowdRange(tagId, userId);
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
}


