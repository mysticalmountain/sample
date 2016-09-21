package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 9/20/16.
 */
@Component
public class BeforeServiceHandlerChain<I, O> implements IServiceHandlerChain<I, O>{

    private Log log = Log.getLog(this.getClass());

    @Autowired
    private List<AbstractBeforeServiceHandler> chain;

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };


    @Override
    public O handle(I i, Service service) throws UnifiedException {
        Integer currentIndex = index.get();
        if (currentIndex < chain.size()) {
//            if (currentIndex == chain.size() - 1) {
//                index.remove();
//            } else {
//                index.set(currentIndex + 1);
//            }
            index.set(currentIndex + 1);
            return (O) chain.get(currentIndex).execute(i, this, service);
        }
        return null;
    }
}
