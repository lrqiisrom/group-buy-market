package com.rom.domain.trade.service;

import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
import com.rom.domain.trade.model.entity.PayActivityEntity;
import com.rom.domain.trade.model.entity.PayDiscountEntity;
import com.rom.domain.trade.model.entity.UserEntity;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @description 交易订单服务接口
 */
public interface ITradeLockOrderService {
    /**
     * 查询，未被支付消费完成的营销优惠订单
     *
     * @param userId     用户ID
     * @param outTradeNo 外部唯一单号
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    /**
     * 查询拼团进度
     *
     * @param teamId 拼团ID
     * @return 进度
     */
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);
    /**
     * 锁定，营销预支付订单；商品下单前，预购锁定。
     *
     * @param userEntity        用户根实体对象
     * @param payActivityEntity 拼团，支付活动实体对象
     * @param payDiscountEntity 拼团，支付优惠实体对象
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception;
}
