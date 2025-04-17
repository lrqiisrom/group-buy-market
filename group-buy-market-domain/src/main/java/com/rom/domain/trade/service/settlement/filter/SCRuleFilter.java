package com.rom.domain.trade.service.settlement.filter;

import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.rom.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.rom.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.rom.types.design.framework.link.model2.handler.ILogicHandler;
import com.rom.types.enums.ResponseCode;
import com.rom.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description SC 渠道来源过滤 - 当某个签约渠道下架后，则不会记账
 */
@Slf4j
@Service
public class SCRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {
    @Resource
    private ITradeRepository repository;
    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-渠道黑名单校验{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        //判断当前渠道是否被过滤
        boolean scBlackIntercept = repository.isSCBlackIntercept(requestParameter.getSource(), requestParameter.getChannel());
        if(scBlackIntercept){
            log.error("{}{} 渠道处于黑名单", requestParameter.getSource(), requestParameter.getChannel());
            throw new AppException(ResponseCode.E0105);

        }
        return next(requestParameter, dynamicContext);
    }
}
