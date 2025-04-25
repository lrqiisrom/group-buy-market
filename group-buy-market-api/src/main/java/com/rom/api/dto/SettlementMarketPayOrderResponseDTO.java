package com.rom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结算应答对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementMarketPayOrderResponseDTO {
    /** 用户ID */
    private String userId;
    /** 拼单组队ID */
    private String teamId;
    /** 活动ID */
    private Long activityId;
    /** 外部交易单号 */
    private String outTradeNo;
}
