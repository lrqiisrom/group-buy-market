package com.rom.infrastructure.adapter.repository;


import com.rom.infrastructure.dcc.DCCService;
import com.rom.infrastructure.redis.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * 仓储抽象类
 */
public abstract class AbstractRepository {
    private final Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    @Resource
    protected IRedisService redisService;
    @Resource
    protected DCCService dccService;

    protected <T> T getFromCacheOrDb(String cacheKey, Supplier<T> dbFallback) {
        if (dccService.isCacheOpenSwitch()) {
            T cacheResult = redisService.getValue(cacheKey);
            if (null != cacheResult) {
                return cacheResult;
            }
            T dbResult = dbFallback.get();
            if (null == dbResult) return null;
            redisService.setValue(cacheKey, dbResult);
            return dbResult;
        } else {
            // 缓存未开启，直接从数据库获取
            logger.warn("缓存降级 {}", cacheKey);
            return dbFallback.get();
        }
    }
}
