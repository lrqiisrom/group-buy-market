package com.rom.api;

import com.rom.api.dto.GoodsMarketRequestDTO;
import com.rom.api.dto.GoodsMarketResponseDTO;
import com.rom.api.response.Response;

/**
 * 营销首页服务接口(获取相关数据)
 */

public interface IMarketIndexService {
    /**
     * 查询拼团营销配置
     *
     * @param requestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupButMarketConfig(GoodsMarketRequestDTO requestDTO);
}
