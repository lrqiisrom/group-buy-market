package com.rom.infrastructure.dao;

import com.rom.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回调任务DAO
 */
@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);
}
