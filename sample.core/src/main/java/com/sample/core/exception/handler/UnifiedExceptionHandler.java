package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-18.
 */
//@Component
public class UnifiedExceptionHandler implements IExceptionHandler {
    @Override
    public void handle(UnifiedException ue, GlobalInfo globalInfo, ExceptionHandlerChain exceptionHandlerChain) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ unified exception handler");
    }

    @Override
    public boolean support(UnifiedException ue) {
        if (ue instanceof UnifiedException) {
            return true;
        }
        return false;
    }
}
