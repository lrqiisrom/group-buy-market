package com.rom.types.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 动态配置中心
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    String value() default "";
}
