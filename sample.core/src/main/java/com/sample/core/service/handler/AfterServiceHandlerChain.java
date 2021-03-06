package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by andongxu on 9/23/16.
 */
@Component
public class AfterServiceHandlerChain <I, O> implements IServiceHandlerChain<I, O> {


    private Log log = Log.getLog(this.getClass());

    @Autowired(required = false)
    private List<AbstractAfterVoidServiceHandler> chain;

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    private boolean isSort = false;


    @Override
    public O handle(I i, Service service) throws UnifiedException {
        if (chain == null) {
            return null;
        }
        if (!isSort) {
            Collections.sort(chain, new ServiceHandlerSort());
            isSort = true;
        }
        Integer currentIndex = index.get();
        if (currentIndex < chain.size()) {
            index.set(currentIndex + 1);
            chain.get(currentIndex).execute(i, this, service);
        }
        return null;
    }
}
