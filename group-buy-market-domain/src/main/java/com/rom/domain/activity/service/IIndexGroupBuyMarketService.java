package com.rom.domain.activity.service;

import com.rom.domain.activity.model.entity.MarketProductEntity;
import com.rom.domain.activity.model.entity.TrialBalanceEntity;

public interface IIndexGroupBuyMarketService {
    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;
}
