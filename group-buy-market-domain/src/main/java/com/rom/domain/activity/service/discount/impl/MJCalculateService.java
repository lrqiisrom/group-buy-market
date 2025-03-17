package com.rom.domain.activity.service.discount.impl;

import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.service.discount.AbstractDiscountCalculateService;
import com.rom.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 满减优惠计算
 */

@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("满减优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        //折扣表达式
        String marketExpr =  groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLIT);
        //满多少
        BigDecimal x  =  new BigDecimal(split[0].trim());
        //减多少
        BigDecimal y  =  new BigDecimal(split[1].trim());

        //不满足的情况
        if(originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }
        BigDecimal deductionPrice = originalPrice.subtract(y);

        //小于零的情况
        if(deductionPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        return deductionPrice;
    }
}
