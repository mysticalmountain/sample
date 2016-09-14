package com.sample.test.module.user;

import com.sample.test.module.Adaptor;
import com.sample.test.module.IModuler;
import com.sample.test.module.IProcessor;

/**
 * Created by andongxu on 16-8-10.
 */
public interface IUserService<REQ, RES> extends IModuler<REQ, RES> {

    public RES register(REQ req, IProcessor<REQ, RES> processor, Object ... args) throws Exception;

}
