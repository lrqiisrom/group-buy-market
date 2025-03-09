package com.rom.domain.activity.service.trial.node;

import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.model.entity.TrialBalanceEntity;
import com.rom.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.rom.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.rom.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description 营销优惠节点
 */
@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
