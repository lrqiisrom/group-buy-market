package com.rom.domain.trade.service;

import com.rom.domain.trade.model.entity.TradePaySettlementEntity;
import com.rom.domain.trade.model.entity.TradePaySuccessEntity;

/**
 * @description 拼团交易结算服务接口
 */

public interface ITradeSettlementOrderService {
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;
}
