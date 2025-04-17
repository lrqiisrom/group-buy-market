package com.rom.domain.trade.service.settlement.factory;

import com.rom.domain.trade.model.entity.*;
import com.rom.domain.trade.service.settlement.filter.EndRuleFilter;
import com.rom.domain.trade.service.settlement.filter.OutTradeNoRuleFilter;
import com.rom.domain.trade.service.settlement.filter.SCRuleFilter;
import com.rom.domain.trade.service.settlement.filter.SettableRuleFilter;
import com.rom.types.design.framework.link.model2.LinkArmory;
import com.rom.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 交易结算规则过滤工厂
 */
@Slf4j
@Service
public class TradeSettlementRuleFilterFactory {

    @Bean("tradeSettlementRuleFilter")
    public BusinessLinkedList<TradeSettlementRuleCommandEntity,
            TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter(
            SCRuleFilter scRuleFilter,
            OutTradeNoRuleFilter outTradeNoRuleFilter,
            SettableRuleFilter settableRuleFilter,
            EndRuleFilter endRuleFilter
    ) {
        LinkArmory<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易结算规则过滤链", scRuleFilter, outTradeNoRuleFilter, settableRuleFilter, endRuleFilter);
        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        public MarketPayOrderEntity marketPayOrderEntity;
        public GroupBuyTeamEntity groupBuyTeamEntity;
    }
}
