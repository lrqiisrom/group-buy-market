package com.rom.trigger.http;

import com.alibaba.fastjson.JSON;
import com.rom.api.IMarketTradeService;
import com.rom.api.dto.LockMarketPayOrderRequestDTO;
import com.rom.api.dto.LockMarketPayOrderResponseDTO;
import com.rom.api.response.Response;
import com.rom.domain.trade.model.entity.MarketPayOrderEntity;
import com.rom.domain.trade.model.valobj.GroupBuyProgressVO;
import com.rom.domain.trade.service.ITradeOrderService;
import com.rom.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description 营销交易服务
 */
@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/gbm/trade/")
public class MarketTradeController implements IMarketTradeService {
    @Resource
    private ITradeOrderService tradeOrderService;

    @Override
    public Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO) {
        String userId = lockMarketPayOrderRequestDTO.getUserId();
        String outTradeNo = lockMarketPayOrderRequestDTO.getOutTradeNo();
        String goodsId = lockMarketPayOrderRequestDTO.getGoodsId();
        Long activityId = lockMarketPayOrderRequestDTO.getActivityId();
        String channel = lockMarketPayOrderRequestDTO.getChannel();
        String source = lockMarketPayOrderRequestDTO.getSource();
        String teamId = lockMarketPayOrderRequestDTO.getTeamId();

        log.info("营销交易锁单:{} LockMarketPayOrderRequestDTO:{}", userId, JSON.toJSONString(lockMarketPayOrderRequestDTO));

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(source) || StringUtils.isBlank(channel) || StringUtils.isBlank(goodsId) || null == activityId) {
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                    .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                    .build();
        }

        MarketPayOrderEntity marketPayOrderEntity = tradeOrderService.queryNoPayMarketPayOrderByOutTradeNo(userId, outTradeNo);
        if (null != marketPayOrderEntity) {
            LockMarketPayOrderResponseDTO lockMarketPayOrderResponseDTO = LockMarketPayOrderResponseDTO.builder()
                    .orderId(marketPayOrderEntity.getOrderId())
                    .deductionPrice(marketPayOrderEntity.getDeductionPrice())
                    .tradeOrderStatus(marketPayOrderEntity.getTradeOrderStatusEnumVO().getCode())
                    .build();
            log.info("交易锁单记录(存在):{} marketPayOrderEntity:{}", userId, JSON.toJSONString(marketPayOrderEntity));
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(lockMarketPayOrderResponseDTO)
                    .build();
        }
        if (null != teamId) {
            GroupBuyProgressVO groupBuyProgressVO = tradeOrderService.queryGroupBuyProgress(teamId);
            if(null != groupBuyProgressVO && Objects.equals(groupBuyProgressVO.getTargetCount(), groupBuyProgressVO.getCompleteCount())){
                log.info("交易锁单拦截-拼单目标已达成:{} {}", userId, teamId);
                return Response.<LockMarketPayOrderResponseDTO>builder()
                        .code(ResponseCode.E0006.getCode())
                        .info(ResponseCode.E0006.getInfo())
                        .build();
            }
        }

        return null;
    }
}
