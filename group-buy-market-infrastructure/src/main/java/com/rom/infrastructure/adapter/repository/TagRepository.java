package com.rom.infrastructure.adapter.repository;

import com.rom.domain.tag.adapter.repository.ITagRepository;
import com.rom.domain.tag.model.entity.CrowdTagsJobEntity;
import com.rom.infrastructure.dao.ICrowdTagsDao;
import com.rom.infrastructure.dao.ICrowdTagsDetailDao;
import com.rom.infrastructure.dao.ICrowdTagsJobDao;
import com.rom.infrastructure.dao.po.CrowdTags;
import com.rom.infrastructure.dao.po.CrowdTagsDetail;
import com.rom.infrastructure.dao.po.CrowdTagsJob;
import com.rom.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 人群标签仓储实现
 */
@Repository
public class TagRepository implements ITagRepository {
    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;

    @Resource
    private IRedisService redisService;
    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        CrowdTagsJob crowdTagsJobReq = new CrowdTagsJob();
        crowdTagsJobReq.setTagId(tagId);
        crowdTagsJobReq.setBatchId(batchId);
        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        if (null == crowdTagsJobRes) return null;
        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }

    /**
     * 添加用户-人群标签
     * 存储到redis中，通过bitset来实现高效存储，每个用户代表一个索引
     * @param tagId 人群标签
     * @param userId 用户id
     */
    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetailReq = new CrowdTagsDetail();
        crowdTagsDetailReq.setTagId(tagId);
        crowdTagsDetailReq.setUserId(userId);

        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);
            //获取BitSet
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId),  true);
        } catch (DuplicateKeyException ignore) {
            //忽略唯一所引冲突
//            RBitSet bitSet = redisService.getBitSet(tagId);
//            bitSet.set(redisService.getIndexFromUserId(userId),  true);
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTagsReq = new CrowdTags();
        crowdTagsReq.setTagId(tagId);
        crowdTagsReq.setStatistics(count);

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }
}
