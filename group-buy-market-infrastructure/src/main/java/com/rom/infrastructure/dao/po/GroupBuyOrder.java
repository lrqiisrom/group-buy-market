package com.rom.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户拼单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupBuyOrder {
    /** 自增ID */
    private Long id;
    /** 拼单组队ID */
    private String team_id;
    /** 活动ID */
    private Long activity_id;
    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 原始价格 */
    private BigDecimal original_price;
    /** 折扣金额 */
    private BigDecimal deduction_price;
    /** 支付价格 */
    private BigDecimal pay_price;
    /** 目标数量 */
    private Integer target_count;
    /** 完成数量 */
    private Integer complete_count;
    /** 锁单数量 */
    private Integer lock_count;
    /** 状态（0-拼单中、1-完成、2-失败） */
    private Integer status;
    /** 创建时间 */
    private Date create_time;
    /** 更新时间 */
    private Date update_time;
}
