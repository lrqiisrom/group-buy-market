package com.rom.domain.trade.service;

import com.rom.domain.trade.model.entity.MarketPayOrderEntity;

/**
 * 交易订单服务接口
 */
public interface ITradeOrderService {
    /**
     * 查询，未被支付消费完成的营销优惠订单
     * @param userId    用户ID
     * @param outTradeNo 外部唯一单号
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity queryNoPayMarketOrderByOutTradeNo(String userId, String outTradeNo);

}
