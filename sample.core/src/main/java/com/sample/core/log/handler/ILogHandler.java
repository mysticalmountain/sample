package com.sample.core.log.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.LogInfo;

/**
 * Created by andongxu on 16-8-22.
 */
public interface ILogHandler<D> {

    public void handle(LogInfo<D> logInfo, LogHandlerChain chain) throws UnifiedException;

//    public boolean support(LogInfo<D> logInfo);

}
