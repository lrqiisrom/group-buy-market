package com.rom.domain.activity.service.trial.thread;

import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.model.valobj.SCSkuActivityVO;

import java.util.concurrent.Callable;

/**
 * 实现查询多线程(将查询的结果放入call函数中)
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    private final String source;

    private final String channel;

    private final String goodsId;

    private final IActivityRepository activityRepository;

    //构造函数注入
    public QueryGroupBuyActivityDiscountVOThreadTask(String source, String channel, String goodsId, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        SCSkuActivityVO scSkuActivityVO =  activityRepository.querySCSkuActivityBySCGoodsId(source, channel, goodsId);
        if (scSkuActivityVO == null) return null;
        return activityRepository.queryGroupBuyActivityDiscount(scSkuActivityVO.getActivityId());
    }
}
