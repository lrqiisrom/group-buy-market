package com.rom.infrastructure.adapter.repository;

import com.rom.domain.tag.adapter.repository.ITagRepository;
import com.rom.domain.tag.model.entity.CrowdTagsJobEntity;
import com.rom.infrastructure.dao.ICrowdTagsDao;
import com.rom.infrastructure.dao.ICrowdTagsDetailDao;
import com.rom.infrastructure.dao.ICrowdTagsJobDao;
import com.rom.infrastructure.dao.po.CrowdTagsJob;
import com.rom.infrastructure.redis.IRedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {

    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {

    }
}
