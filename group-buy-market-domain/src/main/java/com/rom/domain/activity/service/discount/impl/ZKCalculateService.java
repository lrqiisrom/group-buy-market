package com.rom.domain.activity.service.discount.impl;

import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 折扣优惠价格计算
 */
@Slf4j
@Service("ZK")
public class ZKCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("折扣优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        String marketExpr =  groupBuyDiscount.getMarketExpr();
        BigDecimal deductionPrice = originalPrice.multiply(new BigDecimal(marketExpr));
        if (deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        return deductionPrice;
    }
}
