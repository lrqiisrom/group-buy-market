package com.rom.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 渠道商品活动配置关联表
 * 与拼团活动关联（比Sku多了活动id字段）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SCSkuActivity {
    /** 自增ID */
    private Long id;
    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 活动ID */
    private Long activityId;
    /** 商品ID */
    private String goodsId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
