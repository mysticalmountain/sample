package com.sample.test.module.user;

import com.sample.test.module.IModuler;
import com.sample.test.module.IProcessor;
import com.sun.org.apache.regexp.internal.RESyntaxException;

/**
 * Created by andongxu on 16-8-12.
 */
public interface IReversal<REQ, RES> extends IModuler<REQ, RES> {

    public RES reversal(REQ req, IProcessor<REQ, RES> processor, Object ... args) throws Exception;

}
