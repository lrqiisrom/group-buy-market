package com.rom.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品信息
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {
    /** 商品ID */
    private String goodsId;
    /** 商品名称 **/
    private String goodsName;
    /** 商品价格 **/
    private BigDecimal originalPrice;
}
