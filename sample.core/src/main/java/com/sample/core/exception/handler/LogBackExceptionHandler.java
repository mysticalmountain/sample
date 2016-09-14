package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-7-29.
 */
@Component
public class LogBackExceptionHandler extends AbstractExceptionHandler {

    private Log log = Log.getLog(LogBackExceptionHandler.class);

    @Override
    public void doHandle(UnifiedException ue, GlobalInfo gi) {

    }

    @Override
    public boolean support(UnifiedException ue) {

        // TODO: 16-7-29　根据异常等级判断是否支持
        return false;
    }
}
