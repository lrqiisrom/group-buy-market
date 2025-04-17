package com.rom.domain.trade.service.settlement.filter;

import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
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
 * @description 外部交易单号过滤；外部交易单号是否为退单
 */
@Slf4j
@Service
public class OutTradeNoRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {
    @Resource
    private ITradeRepository repository;
    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-外部交易单号过滤{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        //判断当前外部交易单号是否失效
        MarketPayOrderEntity marketPayOrderEntity = repository.queryNoPayMarketPayOrderByOutTradeNo(requestParameter.getUserId(), requestParameter.getOutTradeNo());
        if(null == marketPayOrderEntity){
            log.error("不存在外部单号或用户已退单 {} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
            throw new AppException(ResponseCode.E0104);
        }
        dynamicContext.setMarketPayOrderEntity(marketPayOrderEntity);
        return next(requestParameter, dynamicContext);
    }
}
