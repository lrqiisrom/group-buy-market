package com.rom.domain.trade.service.lock.filter;

import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.entity.GroupBuyActivityEntity;
import com.rom.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.rom.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.rom.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.rom.types.design.framework.link.model2.handler.ILogicHandler;
import com.rom.types.enums.ResponseCode;
import com.rom.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户参与次数、规则限制
 */
@Slf4j
@Service
public class UserTakeLimitRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {
    @Resource
    private ITradeRepository repository;
    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        GroupBuyActivityEntity groupBuyActivityEntity = dynamicContext.getGroupBuyActivity();
        Integer takeLimitCount = groupBuyActivityEntity.getTakeLimitCount();
        Integer count = repository.queryOrderCountByActivityId(requestParameter.getActivityId(), requestParameter.getUserId());
        if(null != takeLimitCount && count >= takeLimitCount) {
            log.info("用户参与次数校验，已达可参与上限 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0103);
        }
        dynamicContext.setUserTakeOrderCount(count);
        return next(requestParameter, dynamicContext);
    }
}
