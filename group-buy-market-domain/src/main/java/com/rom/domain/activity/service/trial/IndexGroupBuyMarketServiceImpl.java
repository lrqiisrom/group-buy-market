package com.rom.domain.activity.service.trial;

import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.model.entity.TrialBalanceEntity;
import com.rom.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.rom.types.design.framework.tree.StrategyHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @description 首页营销服务
 */
@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService{
    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;
    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {
        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();
        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());
        return trialBalanceEntity;
    }
}
