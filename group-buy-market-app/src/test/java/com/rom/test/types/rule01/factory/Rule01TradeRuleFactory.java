package com.rom.test.types.rule01.factory;

import com.rom.test.types.rule01.logic.RuleLogic101;
import com.rom.test.types.rule01.logic.RuleLogic102;
import com.rom.types.design.framework.link.model1.ILogicLink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class Rule01TradeRuleFactory {
    @Resource
    RuleLogic101 ruleLogic101;
    @Resource
    RuleLogic102 ruleLogic102;

    public ILogicLink<String, DynamicContext, String> openLogicLink() {
        ruleLogic101.appendNext(ruleLogic102);
        return ruleLogic101;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
