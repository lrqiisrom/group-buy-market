package com.rom.test.types.rule01.logic;

import com.rom.test.types.rule01.factory.Rule01TradeRuleFactory;
import com.rom.types.design.framework.link.model1.AbstractLogicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RuleLogic102 extends AbstractLogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> {
    @Override
    public String apply(String requestParameter, Rule01TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("RuleLogic102");
        return "lrq";
    }
}
