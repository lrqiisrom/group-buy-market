package com.rom.api;


import com.rom.api.dto.LockMarketPayOrderRequestDTO;
import com.rom.api.dto.LockMarketPayOrderResponseDTO;
import com.rom.api.dto.SettlementMarketPayOrderRequestDTO;
import com.rom.api.dto.SettlementMarketPayOrderResponseDTO;
import com.rom.api.response.Response;

/**
 * @description 营销交易服务接口
 */

public interface IMarketTradeService {

    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);
    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);
}
