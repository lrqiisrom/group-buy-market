package com.rom.test.infrastructure.dao;

import com.rom.infrastructure.dao.po.GroupBuyActivity;
import com.alibaba.fastjson.JSON;
import com.rom.infrastructure.dao.IGroupBuyActivityDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupBuyActivityDaoTest {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Test
    public void test_queryGroupBuyActivityList() {
        List<GroupBuyActivity> groupBuyActivities = groupBuyActivityDao.queryGroupBuyActivityList();
        log.info("测试结果:{}", JSON.toJSONString(groupBuyActivities));
    }
    @Test
    public void test_queryValidGroupBuyActivity() {
        GroupBuyActivity groupBuyActivityReq = new GroupBuyActivity();
        groupBuyActivityReq.setSource("s01");
        groupBuyActivityReq.setChannel("c01");
        GroupBuyActivity queryValidGroupBuyActivity = groupBuyActivityDao.queryValidGroupBuyActivity(groupBuyActivityReq);
        log.info("测试结果:{}", JSON.toJSONString(queryValidGroupBuyActivity));
    }

}
