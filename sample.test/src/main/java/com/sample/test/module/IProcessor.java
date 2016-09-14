package com.sample.test.module;

/**
 * Created by andongxu on 16-8-10.
 */
public interface IProcessor<REQ, RES> {

    public RES processor(REQ req, Object ... args) throws Exception;

}
