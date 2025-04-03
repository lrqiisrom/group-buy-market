package com.rom.domain.trade.adapter.repository;

import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * 交易仓储服务接口
 */
public interface ITradeRepository {
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);
}
