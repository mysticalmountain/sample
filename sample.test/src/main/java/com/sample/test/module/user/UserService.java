package com.sample.test.module.user;

import com.sample.test.module.IProcessor;
import com.sample.test.user.domain.User;
import com.sample.test.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-8-10.
 */
public class UserService<REQ, RES> implements IUserService<REQ, RES> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RES register(REQ req, IProcessor<REQ, RES> processor, Object... args) throws Exception {


        return null;
    }

    @Override
    public void init() throws Throwable {

    }

    @Override
    public void destory() throws Throwable {

    }
}
