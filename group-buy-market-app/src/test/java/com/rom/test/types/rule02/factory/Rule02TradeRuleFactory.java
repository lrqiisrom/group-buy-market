package com.rom.test.types.rule02.factory;

import com.rom.test.types.rule02.logic.RuleLogic201;
import com.rom.test.types.rule02.logic.RuleLogic202;
import com.rom.test.types.rule02.logic.XxxResponse;
import com.rom.types.design.framework.link.model2.LinkArmory;
import com.rom.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Rule02TradeRuleFactory {

    @Bean("demo01")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo01(RuleLogic201 ruleLogic201) {
        LinkArmory<String, DynamicContext, XxxResponse> linkArmory = new LinkArmory<>("demo02", ruleLogic201);
        return linkArmory.getLogicLink();
    }

    @Bean("demo02")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo02(RuleLogic201 ruleLogic201, RuleLogic202 ruleLogic202) {
        LinkArmory<String, DynamicContext, XxxResponse> linkArmory = new LinkArmory<>("demo02", ruleLogic201, ruleLogic202);
        return linkArmory.getLogicLink();
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
