package com.sample.core.log.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.LogInfo;

/**
 * Created by andongxu on 9/10/16.
 */
public abstract class AbstractLogHandler<D> implements ILogHandler<D> {

    public void handle(LogInfo<D> logInfo, LogHandlerChain chain) throws UnifiedException {
        if (support(logInfo)) {
            //TODO 考虑异步处理
            this.doHandle(logInfo);
        }else {
            chain.handle(logInfo);
        }
    }

    public abstract void doHandle(LogInfo<D> logInfo) throws UnifiedException;

    public abstract boolean support(LogInfo<D> logInfo);
}
