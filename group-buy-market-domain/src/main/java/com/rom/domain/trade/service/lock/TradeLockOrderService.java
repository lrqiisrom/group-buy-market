package com.rom.domain.trade.service.lock;


import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.rom.domain.trade.model.entity.*;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;
import com.rom.domain.trade.service.ITradeLockOrderService;
import com.rom.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.rom.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 交易订单服务
 */
@Slf4j
@Service
public class TradeLockOrderService implements ITradeLockOrderService {

    @Resource
    private ITradeRepository repository;
    @Resource
    BusinessLinkedList<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter;
    @Override
    public MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo) {
        log.info("拼团交易-查询未支付营销订单:{} outTradeNo:{}", userId, outTradeNo);
        return repository.queryNoPayMarketPayOrderByOutTradeNo(userId, outTradeNo);
    }

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        log.info("拼团交易-查询拼单进度:{}", teamId);
        return repository.queryGroupBuyProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception {
        log.info("拼团交易-锁定营销优惠支付订单:{} activityId:{} goodsId:{}", userEntity.getUserId(), payActivityEntity.getActivityId(), payDiscountEntity.getGoodsId());
        //交易规则过滤
        TradeLockRuleFilterBackEntity tradeLockRuleFilterBackEntity = tradeRuleFilter.apply(
                TradeLockRuleCommandEntity.builder()
                        .activityId(payActivityEntity.getActivityId())
                        .userId(userEntity.getUserId())
                        .build(),
                new TradeLockRuleFilterFactory.DynamicContext()
        );
        Integer userTakeOrderCount = tradeLockRuleFilterBackEntity.getUserTakeOrderCount();
        GroupBuyOrderAggregate groupBuyOrderAggregate = GroupBuyOrderAggregate.builder()
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .userEntity(userEntity)
                .userTakeOrderCount(userTakeOrderCount)
                .build();
        return repository.lockMarketPayOrder(groupBuyOrderAggregate);
    }
}
