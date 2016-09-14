package com.sample.test.service.user;

import com.sample.core.exception.UnifiedException;
import com.sample.test.service.IServiceHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andongxu on 16-8-17.
 */
public class ServiceHandlerChain {

    private List<IServiceHandler> chain = new LinkedList<IServiceHandler>();

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };


    public <ReqDto, O> O handler(ReqDto i) throws UnifiedException {
        Integer currentIndex = index.get();
        if (currentIndex < chain.size()) {
            if (currentIndex == chain.size() - 1) {
                index.remove();
            }else {
                index.set(currentIndex + 1);
            }
            if (chain.get(currentIndex).support(i)) {
                return (O) chain.get(currentIndex).execute(i);
            }
        }
        return null;
    }


}
