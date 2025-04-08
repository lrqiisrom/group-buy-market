package com.rom.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 拼团交易命令实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeRuleCommandEntity {
    private String userId;
    private Long activityId;
}
