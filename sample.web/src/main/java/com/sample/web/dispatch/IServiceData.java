package com.sample.web.dispatch;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 9/11/16.
 */
public interface IServiceData<D> {

    public D get() throws UnifiedException;


}
