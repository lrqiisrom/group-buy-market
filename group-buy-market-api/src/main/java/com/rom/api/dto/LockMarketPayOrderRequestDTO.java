package com.rom.api.dto;


import lombok.Data;

/**
 * @description 营销支付锁单请求对象
 */

@Data
public class LockMarketPayOrderRequestDTO {
    //用户ID
    private String userId;
    //外部交易单号
    private String outTradeNo;
    //拼单id
    private String teamId;
    //来源
    private String source;
    //渠道
    private String channel;
    //商品id
    private String goodsId;
    //活动id
    private Long activityId;
}
