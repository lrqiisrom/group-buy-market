package com.rom.api;

import com.rom.api.response.Response;

/**
 * DCC 动态配置中心
 * 外部调用接口
 */
public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
