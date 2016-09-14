package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.service.handler.IServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andongxu on 16-8-17.
 */
@Component
public class ServiceHandlerChain {

    private Log log = Log.getLog(this.getClass());

    @Autowired
    private List<IServiceHandler> chain;

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };


    public <I, O> O handle(I i) throws UnifiedException {
        Integer currentIndex = index.get();
        if (currentIndex < chain.size()) {
            if (currentIndex == chain.size() - 1) {
                index.remove();
            } else {
                index.set(currentIndex + 1);
            }
            return (O) chain.get(currentIndex).execute(i, this);
        }
        log.error("Service handler not found");
        return null;
    }


}
