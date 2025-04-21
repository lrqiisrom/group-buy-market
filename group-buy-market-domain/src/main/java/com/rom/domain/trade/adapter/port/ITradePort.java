package com.rom.domain.trade.adapter.port;

import com.rom.domain.trade.model.entity.NotifyTaskEntity;

/**
 * 交易接口服务接口（上锁回调）
 */
public interface ITradePort {
    public String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;
}
