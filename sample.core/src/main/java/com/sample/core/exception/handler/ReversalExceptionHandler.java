package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.TransactionException;
import com.sample.core.exception.UnifiedException;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-1.
 */
@Component
public class ReversalExceptionHandler extends AbstractExceptionHandler {

    @Override
    public void doHandle(UnifiedException ue, GlobalInfo gi) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ reversal exception handler");
    }

    @Override
    public boolean support(UnifiedException ue) {

        if (ue instanceof TransactionException) {
            return true;
        }
        return false;
    }
}
