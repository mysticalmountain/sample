package com.sample.core.exception.handler;

import com.sample.core.exception.GlobalInfo;
import com.sample.core.exception.UnifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 16-7-29.
 */
@Component
public class ExceptionHandlerChain {

    @Autowired
    private List<IExceptionHandler> chain;
//    private List<IExceptionHandler> chain = new LinkedList<IExceptionHandler>();

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    public boolean handleException(UnifiedException ue, GlobalInfo globalInfo) {
        Integer currentIndex = index.get();
        boolean isLast = false;
        if (currentIndex < chain.size()) {
            if (currentIndex == chain.size() - 1) {
                isLast = true;
                index.remove();
            }else {
                index.set(currentIndex + 1);
            }
            globalInfo.setLast(isLast);
            chain.get(currentIndex).handle(ue, globalInfo, this);
        }
        return true;
    }


    public List<IExceptionHandler> getChain() {
        return chain;
    }

    public void setChain(List<IExceptionHandler> chain) {
        this.chain = chain;
    }

    public ThreadLocal<Integer> getIndex() {
        return index;
    }

    public void setIndex(ThreadLocal<Integer> index) {
        this.index = index;
    }
}
