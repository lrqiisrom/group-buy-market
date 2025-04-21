package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 回调任务DAO
 */
@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);

    List<NotifyTask> queryUnExecutedNotifyTaskList();

    NotifyTask queryUnExecutedNotifyTaskListByTeamId(String teamId);

    int updateNotifyTaskStatusRetry(String teamId);

    int updateNotifyTaskStatusError(String teamId);

    int updateNotifyTaskStatusSuccess(String teamId);
}
