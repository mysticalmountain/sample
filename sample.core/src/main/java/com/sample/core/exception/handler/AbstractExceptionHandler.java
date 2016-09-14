package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-7-29.
 */
public abstract class AbstractExceptionHandler implements IExceptionHandler {

    private boolean isAsyn = false;

    @Override
    public void handle(UnifiedException ue, GlobalInfo gi, ExceptionHandlerChain chain) {

        if (support(ue)) {
            if (isAsyn) {
                gi.getExceptionHandlerExecutor().execute(new ExceptionHandlerRunnable(ue, gi));
            } else {
                this.doHandle(ue, gi);
            }
        }
        if (!gi.isLast()) {
            chain.handleException(ue, gi);
        }

    }

    public abstract void doHandle(UnifiedException ue, GlobalInfo gi);

    public class ExceptionHandlerRunnable implements Runnable {

        private UnifiedException ue;
        private GlobalInfo gi;

        public ExceptionHandlerRunnable(UnifiedException ue, GlobalInfo gi) {
            this.ue = ue;
            this.gi = gi;
        }

        @Override
        public void run() {
            doHandle(ue, gi);
        }
    }


    public boolean isAsyn() {
        return isAsyn;
    }

    public void setAsyn(boolean asyn) {
        isAsyn = asyn;
    }
}
