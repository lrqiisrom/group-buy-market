package com.rom.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 拼团结算命令实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSettlementRuleCommandEntity {
    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 用户ID */
    private String userId;
    /** 外部交易单号-确保外部调用唯一幂等 */
    private String outTradeNo;
    /** 外部交易时间 */
    private Date outTradeTime;
}
