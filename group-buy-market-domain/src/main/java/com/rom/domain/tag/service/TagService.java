package com.rom.domain.tag.service;

import com.rom.domain.tag.adapter.repository.ITagRepository;
import com.rom.domain.tag.model.entity.CrowdTagsJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 人群标签服务
 */
@Slf4j
@Service
public class TagService implements ITagService{

    @Resource
    private ITagRepository repository;
    @Override
    public void execTagBatchJob(String tagId, String batchId) {
        log.info("人群标签批次任务 tagId:{} batchId:{}", tagId, batchId);

        //1.查询批次任务
        CrowdTagsJobEntity crowdTagsJobEntity = repository.queryCrowdTagsJobEntity(tagId, batchId);
        //2.采集用户数据 todo

        //3.数据写入记录
        List<String> userIdList = new ArrayList<String>() {
            {
                add("xiaofuge");
                add("rom");
                add("mky");
                add("xfg02");
                add("xfg03");
                add("xfg04");
                add("xfg05");
                add("xfg06");
                add("xfg07");
            }
        };
        //4.一般人群标签的处理在公司中，会有专门的数据数仓团队通过脚本方式写入到数据库，就不用这样一个个或者批次来写。
        for (String userId : userIdList){
            repository.addCrowdTagsUserId(tagId, userId);
        }
        // 5. 更新人群标签统计量
        repository.updateCrowdTagsStatistics(tagId, userIdList.size());
    }
}
