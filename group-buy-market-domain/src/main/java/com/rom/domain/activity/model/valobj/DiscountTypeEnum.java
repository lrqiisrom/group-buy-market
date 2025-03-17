package com.rom.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 折扣类型
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DiscountTypeEnum {
    BASE(0, "基础优惠"),
    TAG(1, "人群标签");

    private Integer code;
    private String info;

    public static DiscountTypeEnum get(Integer code) {
        switch (code) {
            case 0:
                return BASE;
            case 1:
                return TAG;
            default:
                throw new RuntimeException("error code!");
        }
    }
}
