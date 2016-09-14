package com.sample.core.log.api;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.LogInfo;

/**
 * Created by andongxu on 16-8-22.
 */
public interface ILogManager<P> {

    public void write(LogInfo<P> logInfo) throws UnifiedException;

}
