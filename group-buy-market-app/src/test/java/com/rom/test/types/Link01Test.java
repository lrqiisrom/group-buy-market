package com.rom.test.types;

import com.alibaba.fastjson.JSON;
import com.rom.test.types.rule01.factory.Rule01TradeRuleFactory;
import com.rom.types.design.framework.link.model1.ILogicLink;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Link01Test {
    @Resource
    Rule01TradeRuleFactory rule01TradeRuleFactory;

    @Test
    public void testRule01() throws Exception {
        ILogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> logicLink = rule01TradeRuleFactory.openLogicLink();
        String logic = logicLink.apply("123", new Rule01TradeRuleFactory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(logic));
    }
}
