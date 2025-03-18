package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人群标签DAO
 */
@Mapper
public interface ICrowdTagsDao {
    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);
}
