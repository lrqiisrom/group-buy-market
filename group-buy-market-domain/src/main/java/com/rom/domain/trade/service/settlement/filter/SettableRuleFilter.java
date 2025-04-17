package com.rom.domain.trade.service.settlement.filter;

import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.entity.GroupBuyTeamEntity;
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
import java.util.Date;

/**
 * @description 可结算规则过滤；交易时间
 */
@Slf4j
@Service
public class SettableRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {
    @Resource
    private ITradeRepository repository;
    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-有效时间校验{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        //判断结算时间是否在有效期内
        MarketPayOrderEntity marketPayOrderEntity = dynamicContext.getMarketPayOrderEntity();
        GroupBuyTeamEntity groupBuyTeamEntity = repository.queryGroupBuyTeamByTeamId(marketPayOrderEntity.getTeamId());
        Date outTradeTime = requestParameter.getOutTradeTime();
        if(!outTradeTime.before(groupBuyTeamEntity.getValidEndTime())) {
            log.error("订单交易时间不在拼团有效时间范围内");
            throw new AppException(ResponseCode.E0106);
        }

        dynamicContext.setGroupBuyTeamEntity(groupBuyTeamEntity);
        return next(requestParameter, dynamicContext);
    }
}
