package com.rom.domain.trade.service;

import com.rom.domain.trade.model.entity.TradePaySettlementEntity;
import com.rom.domain.trade.model.entity.TradePaySuccessEntity;

import java.util.Map;

/**
 * @description 拼团交易结算服务接口
 */

public interface ITradeSettlementOrderService {
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

    Map<String, Integer> execSettlementNotifyJob() throws Exception;

    Map<String, Integer> execSettlementNotifyJob(String teamId) throws Exception;
}
