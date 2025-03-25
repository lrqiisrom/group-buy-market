package com.rom.test;

import com.rom.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    @Resource
    private IRedisService redisService;

    @Test
    public void test_redis() {
        RBitSet bitSet = redisService.getBitSet("redis");
        System.out.println(bitSet.isExists());
    }
    @Test
    public void test() {
        log.info("测试完成");
    }

}
