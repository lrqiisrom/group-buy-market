package com.rom.domain.trade.service.lock.filter;

import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.entity.GroupBuyActivityEntity;
import com.rom.domain.trade.model.entity.TradeRuleCommandEntity;
import com.rom.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.rom.domain.trade.service.lock.factory.TradeRuleFilterFactory;
import com.rom.types.design.framework.link.model2.handler.ILogicHandler;
import com.rom.types.enums.ActivityStatusEnumVO;
import com.rom.types.enums.ResponseCode;
import com.rom.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description 活动的可用性，规则过滤【状态、有效期】
 */

@Slf4j
@Service
public class ActivityUsabilityRuleFilter implements ILogicHandler<TradeRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {
    @Resource
    private ITradeRepository repository;

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleCommandEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-活动的可用性校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());
        GroupBuyActivityEntity groupBuyActivity = repository.queryGroupBuyActivityEntityByActivityId(requestParameter.getActivityId());
        if (!ActivityStatusEnumVO.EFFECTIVE.equals(groupBuyActivity.getStatus())) {
            log.info("活动的可用性校验，非生效状态 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0101);
        }

        //校验活动时间
        Date currentTime = new Date();
        if ( currentTime.before(groupBuyActivity.getStartTime()) || currentTime.after(groupBuyActivity.getEndTime())) {
            log.info("活动的可用性校验，不在有效期内 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0102);
        }
        dynamicContext.setGroupBuyActivityEntity(groupBuyActivity);
        return next(requestParameter, dynamicContext);
    }
}
