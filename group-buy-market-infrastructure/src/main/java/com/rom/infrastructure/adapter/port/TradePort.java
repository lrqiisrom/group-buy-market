package com.rom.infrastructure.adapter.port;

import com.rom.domain.trade.adapter.port.ITradePort;
import com.rom.domain.trade.model.entity.NotifyTaskEntity;
import com.rom.infrastructure.gateway.GroupBuyNotifyService;
import com.rom.infrastructure.redis.IRedisService;
import com.rom.types.enums.NotifyTaskHTTPEnumVO;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class TradePort implements ITradePort {
    @Resource
    private GroupBuyNotifyService groupBuyNotifyService;
    @Resource
    private IRedisService redisService;

    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception {
        RLock lock = redisService.getLock(notifyTask.lockKey());
        try {
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {
                    if(StringUtils.isBlank(notifyTask.getNotifyUrl()) || "暂无".equals(notifyTask.getNotifyUrl())){
                        return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }
                    return groupBuyNotifyService.groupBuyNotify(notifyTask.getNotifyUrl(), notifyTask.getParameterJson());
                } finally {
                    //无论是否报异常 都要解锁，通过若获取锁失败，则中断
                    if(lock.isLocked() && lock.isHeldByCurrentThread()){
                        lock.unlock();
                    }

                }
            }
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }
    }
}
