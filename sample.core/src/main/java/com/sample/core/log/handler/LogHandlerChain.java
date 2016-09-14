package com.sample.core.log.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 16-8-22.
 */
@Component
public class LogHandlerChain {

    @Autowired
    private List<ILogHandler> chain;

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    public void handle(LogInfo logInfo) throws UnifiedException {
        Integer currentIndex = index.get();
        if (currentIndex < chain.size()) {
            if (currentIndex == chain.size() - 1) {
                index.remove();
            }else {
                index.set(currentIndex + 1);
            }
        }
        chain.get(currentIndex).handle(logInfo, this);

    }


}
