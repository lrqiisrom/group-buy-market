package com.rom.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyActivity {
    /** 自增 **/
    private String id;
    /** 活动ID **/
    private String activityId;
    /** 活动名称 **/
    private String activityName;
    /** 来源 **/
    private String source;
    /** 渠道 **/
    private String channel;
    /** 商品ID **/
    private String goodsId;
    /** 折扣ID **/
    private String discountId;
    /** 拼团方式（0自动成团、1达成目标拼团） **/
    private String groupType;
    /** 拼团次数限制 **/
    private String takeLimitCount;
    /** 拼团目标 **/
    private String target;
    /** 拼团时长（分钟） **/
    private String validTime;
    /** 活动状态（0创建、1生效、2过期、3废弃） **/
    private String status;
    /** 活动开始时间 **/
    private String startTime;
    /** 活动结束时间 **/
    private String endTime;
    /** 人群标签规则标识 **/
    private String tagId;
    /** 人群标签规则范围（多选；1可见限制、2参与限制） **/
    private String tagScope;
    /** 创建时间 **/
    private String createTime;
    /** 更新时间 **/
    private String updateTime;
}
