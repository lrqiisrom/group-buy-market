package com.rom.domain.trade.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 回调配置
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifyConfigVO {
    private NotifyTypeEnumVO notifyType;
    private String notifyMQ;
    private String notifyUrl;
}
