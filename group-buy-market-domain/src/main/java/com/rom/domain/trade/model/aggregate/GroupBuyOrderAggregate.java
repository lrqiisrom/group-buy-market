package com.rom.domain.trade.model.aggregate;

import com.rom.domain.trade.model.entity.PayActivityEntity;
import com.rom.domain.trade.model.entity.PayDiscountEntity;
import com.rom.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyOrderAggregate {
    private PayActivityEntity payActivityEntity;
    private PayDiscountEntity payDiscountEntity;
    private UserEntity userEntity;
    private Integer userTakeOrderCount;
}
