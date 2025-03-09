package com.rom.domain.activity.service.trial;


import com.rom.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.rom.types.design.framework.tree.AbstractStrategyRouter;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractStrategyRouter<com.rom.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.rom.domain.activity.model.entity.TrialBalanceEntity> {

}
