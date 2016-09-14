package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-7-29.
 */
public interface IExceptionHandler extends HandlerSupport {

    /**
     *
     * @param ue 统一异常
     * @param globalInfo　异常处理全局信息
     * @param exceptionHandlerChain　异常处理链
     */
    public void handle(UnifiedException ue, GlobalInfo globalInfo, ExceptionHandlerChain exceptionHandlerChain);

}
