package com.rom.domain.trade.service.filter;

import com.rom.domain.trade.model.entity.TradeRuleCommandEntity;
import com.rom.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.rom.domain.trade.service.factory.TradeRuleFilterFactory;
import com.rom.types.design.framework.link.model2.handler.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description 活动的可用性，规则过滤【状态、有效期】
 */

@Slf4j
@Service
public class ActivityUsabilityRuleFilter implements ILogicHandler<TradeRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleCommandEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {

        return next(requestParameter, dynamicContext);
    }
}
