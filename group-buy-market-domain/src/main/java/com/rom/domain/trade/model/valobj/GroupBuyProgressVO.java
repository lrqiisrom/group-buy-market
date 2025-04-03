package com.rom.domain.trade.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description 拼团进度值对象
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyProgressVO {
    /** 目标数量 */
    private Integer targetCount;
    /** 完成数量 */
    private Integer completeCount;
    /** 锁单数量 */
    private Integer lockCount;
}