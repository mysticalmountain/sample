package com.sample.test.service;

import com.sample.core.exception.UnifiedException;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by andongxu on 16-8-15.
 */
public interface IService<I, O> {

    public O service(I i, Object ... args) throws UnifiedException;

}
