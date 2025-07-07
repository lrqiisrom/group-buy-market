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
    //回调配置
    private NotifyConfigVO notifyConfigVO;

    public void setNotifyUrl(String url) {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyType("HTTP");
        notifyConfigVO.setNotifyUrl(url);
        this.notifyConfigVO = notifyConfigVO;
    }

    public void setNotifyMQ() {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyType("MQ");
        this.notifyConfigVO = notifyConfigVO;
    }
    @Data
    public static class NotifyConfigVO {
        private String notifyType;
        private String notifyMQ;
        private String notifyUrl;
    }
}
