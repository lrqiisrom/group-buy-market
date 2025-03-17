package com.rom.domain.activity.service.discount.impl;

import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service("N")
public class NCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("N元购优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        String marketExpr = groupBuyDiscount.getMarketExpr();
        return new BigDecimal(marketExpr);
    }
}
