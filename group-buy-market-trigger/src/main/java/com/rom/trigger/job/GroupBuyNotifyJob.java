package com.rom.trigger.job;

import com.alibaba.fastjson.JSON;
import com.rom.domain.trade.service.ITradeSettlementOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 拼团完结回调通知任务；拼团回调任务表，实际公司场景会定时清理数据结转，不会有太多数据挤压
 */
@Slf4j
@Service
public class GroupBuyNotifyJob {
    @Resource
    private ITradeSettlementOrderService tradeSettlementOrderService;

    @Resource
    private RedissonClient redissonClient;

    @Scheduled(cron = "0/30 * * * * ?")
    public void exec() {
        RLock lock = redissonClient.getLock("group_buy_market_notify_job_exec");
        try {
            boolean isLocked = lock.tryLock(3, 0, TimeUnit.SECONDS);
            if (!isLocked) return;
            Map<String, Integer> result = tradeSettlementOrderService.execSettlementNotifyJob();
            log.info("定时任务，回调通知拼团完结任务 result:{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("定时任务，回调通知拼团完结任务失败", e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
