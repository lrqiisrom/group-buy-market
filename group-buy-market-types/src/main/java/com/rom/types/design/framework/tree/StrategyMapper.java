package com.rom.types.design.framework.tree;

/**
 * @description 策略映射器，根据get方法返回对应的Handler
 * T 入参类型
 * D 上下文参数
 * R 返参类型
 */
public interface StrategyMapper<T, D, R> {
    /**
     * 获取待执行策略
     *
     * @param requestParameter 入参
     * @param dynamicContext   上下文
     * @return 返参
     * @throws Exception 异常
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
