package com.rom.domain.trade.service.lock.factory;

import com.rom.domain.trade.model.entity.GroupBuyActivityEntity;
import com.rom.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.rom.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.rom.domain.trade.service.lock.filter.ActivityUsabilityRuleFilter;
import com.rom.domain.trade.service.lock.filter.TeamStockOccupyRuleFilter;
import com.rom.domain.trade.service.lock.filter.UserTakeLimitRuleFilter;
import com.rom.types.design.framework.link.model2.LinkArmory;
import com.rom.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 交易规则过滤工厂
 */
@Slf4j
@Service
public class TradeLockRuleFilterFactory {
    @Bean("tradeRuleFilter")
    public BusinessLinkedList<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter(
            ActivityUsabilityRuleFilter activityUsabilityRuleFilter,
            UserTakeLimitRuleFilter userTakeLimitRuleFilter,
            TeamStockOccupyRuleFilter teamStockOccupyRuleFilter) {
        // 组装链
        LinkArmory<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易规则过滤链",
                        activityUsabilityRuleFilter,
                        userTakeLimitRuleFilter,
                        teamStockOccupyRuleFilter);

        // 链对象
        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String teamStockKey = "group_buy_market_team_stock_key_";

        GroupBuyActivityEntity groupBuyActivity;

        private Integer userTakeOrderCount;

        public String generateTeamStockKey(String teamId) {
            if(StringUtils.isBlank(teamId)) return null;
            return teamStockKey + groupBuyActivity.getActivityId() + "_" + teamId;
        }

        public String generateRecoveryTeamStockKey(String teamId) {
            if(StringUtils.isBlank(teamId)) return null;
            return teamStockKey + groupBuyActivity.getActivityId() + "_" + teamId + "_recovery";
        }
    }
}
