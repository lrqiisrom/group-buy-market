package com.rom.domain.trade.service;

import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @description 交易订单服务接口
 */
public interface ITradeOrderService {
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
}
