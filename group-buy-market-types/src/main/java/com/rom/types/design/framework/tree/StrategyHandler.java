package com.rom.types.design.framework.tree;


/** 策略处理方法泛型
 * T 入参类型
 * D 上下文参数
 * R 返参类型
 */

public interface StrategyHandler<T, D, R> {
    /** 接口的默认实现实例 **/
    StrategyHandler DEFAULT = (T, D) -> null;//接口的默认实现实例

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
