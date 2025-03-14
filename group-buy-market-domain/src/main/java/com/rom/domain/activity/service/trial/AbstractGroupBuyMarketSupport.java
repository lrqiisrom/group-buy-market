package com.rom.domain.activity.service.trial;


import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.rom.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.rom.types.design.framework.tree.AbstractStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<com.rom.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.rom.domain.activity.model.entity.TrialBalanceEntity> {
    protected long timeout = 500;
    @Resource
    protected IActivityRepository repository;
    @Override
    protected void multiThread(com.rom.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {

    }

}
