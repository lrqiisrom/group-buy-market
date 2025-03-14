package com.rom.domain.activity.service.trial.factory;

import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.model.entity.TrialBalanceEntity;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.model.valobj.SkuVO;
import com.rom.domain.activity.service.trial.node.RootNode;
import com.rom.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity,DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
        private SkuVO skuVO;
    }
}
