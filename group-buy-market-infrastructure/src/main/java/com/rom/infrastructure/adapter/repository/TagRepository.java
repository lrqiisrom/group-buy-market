package com.rom.infrastructure.adapter.repository;

import com.rom.domain.tag.adapter.repository.ITagRepository;
import com.rom.domain.tag.model.entity.CrowdTagsJobEntity;
import com.rom.infrastructure.dao.ICrowdTagsDao;
import com.rom.infrastructure.dao.ICrowdTagsDetailDao;
import com.rom.infrastructure.dao.ICrowdTagsJobDao;
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

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        return null;
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {

    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {

    }
}
