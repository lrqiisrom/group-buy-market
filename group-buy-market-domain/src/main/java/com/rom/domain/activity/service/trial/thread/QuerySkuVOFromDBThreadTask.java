package com.rom.domain.activity.service.trial.thread;

import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.valobj.SkuVO;

import java.util.concurrent.Callable;

/**
 * 实现多线程查询商品信息
 */

public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {

    private final String goodsId;

    private final IActivityRepository activityRepository;

    public QuerySkuVOFromDBThreadTask(String goodsId, IActivityRepository activityRepository) {
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public SkuVO call() throws Exception {
        return activityRepository.querySkuByGoodsId(goodsId);
    }
}
