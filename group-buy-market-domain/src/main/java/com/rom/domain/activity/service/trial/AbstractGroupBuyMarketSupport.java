package com.rom.domain.activity.service.trial;


import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.rom.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.rom.types.design.framework.tree.AbstractStrategyRouter;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<com.rom.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.rom.domain.activity.model.entity.TrialBalanceEntity> {
    @Override
    protected void multiThread(com.rom.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

    }
}
