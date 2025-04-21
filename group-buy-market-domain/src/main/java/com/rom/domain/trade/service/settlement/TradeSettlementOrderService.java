package com.rom.domain.trade.service.settlement;

import com.alibaba.fastjson.JSON;
import com.rom.domain.trade.adapter.port.ITradePort;
import com.rom.domain.trade.adapter.repository.ITradeRepository;
import com.rom.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.rom.domain.trade.model.entity.*;
import com.rom.domain.trade.service.ITradeSettlementOrderService;
import com.rom.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.rom.types.design.framework.link.model2.chain.BusinessLinkedList;
import com.rom.types.enums.NotifyTaskHTTPEnumVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 拼团交易结算服务
 */
@Slf4j
@Service
public class TradeSettlementOrderService implements ITradeSettlementOrderService {
    @Resource
    private ITradeRepository repository;
    @Resource
    private BusinessLinkedList<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter;
    @Resource
    private ITradePort port;
    @Override
    public TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception {
        log.info("拼团交易-支付订单结算:{} outTradeNo:{}", tradePaySuccessEntity.getUserId(), tradePaySuccessEntity.getOutTradeNo());
        //1. 结算规则过滤
        TradeSettlementRuleCommandEntity tradeSettlementRuleCommandEntity = TradeSettlementRuleCommandEntity.builder()
                .source(tradePaySuccessEntity.getSource())
                .channel(tradePaySuccessEntity.getChannel())
                .userId(tradePaySuccessEntity.getUserId())
                .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                .outTradeTime(tradePaySuccessEntity.getOutTradeTime())
                .build();

        TradeSettlementRuleFilterBackEntity tradeSettlementRuleFilterBackEntity =
                tradeSettlementRuleFilter.apply(tradeSettlementRuleCommandEntity, new TradeSettlementRuleFilterFactory.DynamicContext());
        String teamId = tradeSettlementRuleFilterBackEntity.getTeamId();
        // 2. 查询组团信息
        GroupBuyTeamEntity groupBuyTeamEntity = GroupBuyTeamEntity.builder()
                .teamId(tradeSettlementRuleFilterBackEntity.getTeamId())
                .activityId(tradeSettlementRuleFilterBackEntity.getActivityId())
                .targetCount(tradeSettlementRuleFilterBackEntity.getTargetCount())
                .completeCount(tradeSettlementRuleFilterBackEntity.getCompleteCount())
                .lockCount(tradeSettlementRuleFilterBackEntity.getLockCount())
                .status(tradeSettlementRuleFilterBackEntity.getStatus())
                .validStartTime(tradeSettlementRuleFilterBackEntity.getValidStartTime())
                .validEndTime(tradeSettlementRuleFilterBackEntity.getValidEndTime())
                .notifyUrl(tradeSettlementRuleFilterBackEntity.getNotifyUrl())
                .build();
        // 3. 构建聚合对象
        GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate = GroupBuyTeamSettlementAggregate.builder()
                .userEntity(UserEntity.builder().userId(tradePaySuccessEntity.getUserId()).build())
                .groupBuyTeamEntity(groupBuyTeamEntity)
                .tradePaySuccessEntity(tradePaySuccessEntity)
                .build();
        // 4. 拼团交易结算
        boolean isNotify =  repository.settlementMarketPayOrder(groupBuyTeamSettlementAggregate);
        if (isNotify) {
            Map<String, Integer> notifyResultMap = execSettlementNotifyJob(teamId);
            log.info("回调通知拼团完结 result:{}", JSON.toJSONString(notifyResultMap));
        }
        // 5. 返回结算信息 - 公司中开发这样的流程时候，会根据外部需要进行值的设置
        return TradePaySettlementEntity.builder()
                .source(tradePaySuccessEntity.getSource())
                .channel(tradePaySuccessEntity.getChannel())
                .userId(tradePaySuccessEntity.getUserId())
                .teamId(teamId)
                .activityId(groupBuyTeamEntity.getActivityId())
                .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                .build();
    }

    @Override
    public Map<String, Integer> execSettlementNotifyJob() throws Exception {
        log.info("拼团交易-执行结算通知任务");
        //查询未执行任务
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList();

        return execSettlementNotifyJob(notifyTaskEntityList);
    }



    @Override
    public Map<String, Integer> execSettlementNotifyJob(String teamId) throws Exception {
        log.info("拼团交易-执行结算通知回调，指定 teamId:{}", teamId);
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList(teamId);
        return execSettlementNotifyJob(notifyTaskEntityList);
    }

    private Map<String, Integer> execSettlementNotifyJob(List<NotifyTaskEntity> notifyTaskEntityList) throws Exception {
        int successCount = 0, errorCount = 0, retryCount = 0;
        for(NotifyTaskEntity notifyTaskEntity : notifyTaskEntityList) {
            String response = port.groupBuyNotify(notifyTaskEntity);

            if(NotifyTaskHTTPEnumVO.SUCCESS.getCode().equals(response)) {
                int updateCount = repository.updateNotifyTaskStatusSuccess(notifyTaskEntity.getTeamId());
                if (1 == updateCount) {
                    successCount += 1;
                }
            }
            else if (NotifyTaskHTTPEnumVO.ERROR.getCode().equals(response)) {
                if (notifyTaskEntity.getNotifyCount() > 5) {
                    int updateCount = repository.updateNotifyTaskStatusError(notifyTaskEntity.getTeamId());
                    if (1 == updateCount) {
                        errorCount += 1;
                    }
                } else {
                    int updateCount = repository.updateNotifyTaskStatusRetry(notifyTaskEntity.getTeamId());
                    if (1 == updateCount) {
                        retryCount += 1;
                    }
                }
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("waitCount", notifyTaskEntityList.size());
        result.put("successCount", successCount);
        result.put("errorCount", errorCount);
        result.put("retryCount", retryCount);
        return result;
    }

}
