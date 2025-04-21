package com.rom.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @description 回调请求对象
 */

@Data
public class NotifyRequestDTO {
    /** 组队ID */
    private String teamId;
    /** 外部单号 */
    private List<String> outTradeNoList;
}
