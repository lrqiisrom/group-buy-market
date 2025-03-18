package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 人群标签任务
 */
@Mapper
public interface ICrowdTagsJobDao {

    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJobReq);

}
