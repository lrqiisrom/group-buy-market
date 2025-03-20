package com.rom.domain.tag.adapter.repository;

import com.rom.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * 人群标签仓储
 */
public interface ITagRepository {
    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);

}
