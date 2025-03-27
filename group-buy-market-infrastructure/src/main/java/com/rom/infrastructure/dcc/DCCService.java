package com.rom.infrastructure.dcc;

import com.rom.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

/**
 * 动态配置服务
 */

@Service
public class DCCService {
    /**
     * 降级开关 0关闭、1开启
     */
    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    //判断是否降级
    public boolean isDowngradeSwitch() {
        return ("1").equals(downgradeSwitch);
    }
    //判断切量，限流、限权限
    public boolean isCutRange(String userId) {
        //hash绝对值
        int hashCode = Math.abs(userId.hashCode());

        //获取最后两位
        int lastTwoDigits = hashCode % 100;
        if ( lastTwoDigits <= Integer.parseInt(cutRange) ) {
            return true;
        }
        return false;
    }
}
