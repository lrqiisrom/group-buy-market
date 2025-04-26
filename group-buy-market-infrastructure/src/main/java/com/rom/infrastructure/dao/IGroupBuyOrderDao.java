package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 用户拼单
 */
@Mapper
public interface IGroupBuyOrderDao {
    /**
     * @description 新建用户拼单
     * @param groupBuyOrder
     */
    void insert(GroupBuyOrder groupBuyOrder);

    /**
     * @description 锁单
     * @param teamId 拼团组队id
     * @return
     */
    int updateAddLockCount(String teamId);

    /**
     * 更新lock_count
     * @param teamId
     * @return
     */
    int updateSubtractionLockCount(String teamId);

    /**
     * 查询拼单进度（target_count，complete_count，lock_count）
     * @param teamId
     * @return
     */
    GroupBuyOrder queryGroupBuyProgress(String teamId);

    /**
     * 查询拼团信息
     * @param teamId
     * @return
     */
    GroupBuyOrder queryGroupBuyTeamByTeamId(String teamId);

    Integer updateAddCompleteCount(String teamId);

    Integer updateOrderStatus2COMPLETE(String teamId);

    List<GroupBuyOrder> queryGroupBuyProgressByTeamIds(Set<String> teamIds);

    Integer queryAllTeamCount(Set<String> teamIds);

    Integer queryAllTeamCompleteCount(Set<String> teamIds);

    Integer queryAllUserCount(Set<String> teamIds);
}
