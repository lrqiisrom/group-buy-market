package com.rom.infrastructure.adapter.repository;

import com.rom.domain.activity.adapter.repository.IActivityRepository;
import com.rom.domain.activity.model.valobj.DiscountTypeEnum;
import com.rom.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.rom.domain.activity.model.valobj.SCSkuActivityVO;
import com.rom.domain.activity.model.valobj.SkuVO;
import com.rom.infrastructure.dao.IGroupBuyActivityDao;
import com.rom.infrastructure.dao.IGroupBuyDiscountDao;
import com.rom.infrastructure.dao.ISCSkuActivityDao;
import com.rom.infrastructure.dao.ISkuDao;
import com.rom.infrastructure.dao.po.GroupBuyActivity;
import com.rom.infrastructure.dao.po.GroupBuyDiscount;
import com.rom.infrastructure.dao.po.SCSkuActivity;
import com.rom.infrastructure.dao.po.Sku;
import com.rom.infrastructure.dcc.DCCService;
import com.rom.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 活动仓储实现
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    /** DAO查询拼团 */
    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    /** DAO查询折扣 */
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;
    @Resource
    private ISkuDao skuDao;

    @Resource
    private ISCSkuActivityDao skuActivityDao;

    @Resource
    private IRedisService redisService;

    @Resource
    private DCCService dccService;
    /** 根据SC渠道查询商品折扣 */
    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscount(Long activityId) {
//        //初始化一个拼团示例,当作发送查询请求
//        GroupBuyActivity groupBuyActivityReq = new GroupBuyActivity();
//        groupBuyActivityReq.setSource(source);
//        groupBuyActivityReq.setChannel(channel);
        //接受查询结果
        //System.out.println(source + " " + channel);
        //System.out.println(groupBuyActivityReq);
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);
        if (null == groupBuyActivityRes) return null;
        //System.out.println(groupBuyActivityRes);
        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        if (null == groupBuyDiscountRes) return null;
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuById(goodsId);
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityReq = new SCSkuActivity();
        scSkuActivityReq.setSource(source);
        scSkuActivityReq.setChannel(channel);
        scSkuActivityReq.setGoodsId(goodsId);
        SCSkuActivity scSkuActivityRes = skuActivityDao.querySCSkuActivityBySCGoodsId(scSkuActivityReq);
        if (null == scSkuActivityRes) return null;
        return SCSkuActivityVO.builder()
                .source(scSkuActivityRes.getSource())
                .channel(scSkuActivityRes.getChannel())
                .activityId(scSkuActivityRes.getActivityId())
                .goodsId(scSkuActivityRes.getGoodsId())
                .build();
    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {
        RBitSet rBitSet = redisService.getBitSet(tagId);
        if(!rBitSet.isExists()) return true;

        //判断用户是否在人群中
        return rBitSet.get(redisService.getIndexFromUserId(userId));
    }

    @Override
    public boolean downgradeSwitch() {
        return dccService.isDowngradeSwitch();
    }

    @Override
    public boolean cutRange(String userId) {
        return dccService.isCutRange(userId);
    }
}
