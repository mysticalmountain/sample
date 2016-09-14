package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-7-29.
 */
@Component
public class MongoDBExceptionHandler extends AbstractExceptionHandler {



    @Override
    public void doHandle(UnifiedException ue, GlobalInfo gi) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ mongodb exception handler");
    }

    @Override
    public boolean support(UnifiedException ue) {

        // TODO: 16-7-29　根据异常等级判断是否支持
        return false;
    }
}
