package com.rom.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradePaySuccessEntity {
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
