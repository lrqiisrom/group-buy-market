package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人群标签明细
 */
@Mapper
public interface ICrowdTagsDetailDao {
    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);
}
