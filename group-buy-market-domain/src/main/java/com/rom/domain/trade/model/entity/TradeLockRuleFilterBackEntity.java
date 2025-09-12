package com.rom.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户拼团交易反馈实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeLockRuleFilterBackEntity {
    // 用户参与活动的订单量
    private Integer userTakeOrderCount;

    // 恢复组队库存缓存key
    private String recoveryTeamStockKey;
}
