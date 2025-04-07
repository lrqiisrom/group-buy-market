package com.rom.types.design.framework.link.model1;


public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {
    //链的具体逻辑
    R apply(T requestParameter, D dynamicContext) throws Exception;
}
