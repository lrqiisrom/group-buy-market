package com.rom.domain.trade.adapter.repository;

import com.rom.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.rom.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.rom.domain.trade.model.entity.GroupBuyActivityEntity;
import com.rom.domain.trade.model.entity.GroupBuyTeamEntity;
import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
import com.rom.domain.trade.model.entity.NotifyTaskEntity;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;

import java.util.List;

/**
 * 交易仓储服务接口
 */
public interface ITradeRepository {
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);
    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    NotifyTaskEntity settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    boolean isSCBlackIntercept(String source, String channel);

    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList();
    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList(String teamId);
    int updateNotifyTaskStatusSuccess(String teamId);

    int updateNotifyTaskStatusError(String teamId);

    int updateNotifyTaskStatusRetry(String teamId);

    boolean occupyTeamStock(String teamStockKey, String recoveryTeamStockKey, Integer target, Integer validTime);

    void recoveryTeamStock(String recoveryTeamStockKey, Integer validTime);
}
