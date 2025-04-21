package com.rom.infrastructure.gateway;

import com.rom.types.enums.ResponseCode;
import com.rom.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 拼团回调服务
 */

@Slf4j
@Service
public class GroupBuyNotifyService {
    @Resource
    private OkHttpClient okHttpClient;

    public String groupBuyNotify(String apiUrl, String notifyRequestDTOJSON) throws Exception {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, notifyRequestDTOJSON);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();
        }catch (Exception e){
            log.error("拼团回调HTTP接口服务异常 {}", apiUrl, e);
            throw new AppException(ResponseCode.HTTP_EXCEPTION);
        }
    }
}
