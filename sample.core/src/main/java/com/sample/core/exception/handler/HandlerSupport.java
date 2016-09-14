package com.sample.core.exception.handler;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-7-29.
 */
public interface HandlerSupport {

    /**
     * 是否支持该异常处理
     * @param ue
     * @return
     */
    public boolean support(UnifiedException ue);
}
