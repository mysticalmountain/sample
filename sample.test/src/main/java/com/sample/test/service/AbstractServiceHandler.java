package com.sample.test.service;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-8-17.
 */
public abstract class AbstractServiceHandler<I, O> implements IServiceHandler<I, O> {

    //TODO 不知道怎么写
    @Override
    public O execute(I i, Object ... args) throws UnifiedException {
        if (support(i)) {
            return doHandler(i, args);
        }else {
            return null;
        }
    }


    public abstract O doHandler(I i, Object args) throws UnifiedException;
}
