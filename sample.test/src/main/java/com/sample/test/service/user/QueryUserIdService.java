package com.sample.test.service.user;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.test.service.*;
import com.sample.user.model.User;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.model.dto.UsernameReq;
import com.sample.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-8-17.
 */
public class QueryUserIdService<I> implements ICallbackService<I, UserIdRsp> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceHandlerChain userQueryHandlerChain;



    @Override
    public UserIdRsp service(I i, IProcessor callback) throws UnifiedException {
        return userQueryHandlerChain.handler(i);
    }

    @Override
    public UserIdRsp service(I i, Object... args) throws UnifiedException {
        return null;
    }


    class UserQueryByUsername implements IServiceHandler<UsernameReq, UserIdRsp> {
        @Override
        public UserIdRsp execute(UsernameReq usernameReq, Object... args) throws UnifiedException {
            User user = userRepository.findByUsername(usernameReq.getUsername());
            if (user == null) {
                UnifiedExceptionUtil.throwCommonException("999", "用户以存在", "user", null, null);
            }
            com.sample.user.model.dto.UserIdRsp rsp = new com.sample.user.model.dto.UserIdRsp();
            rsp.setErrorCode("000000");
            rsp.setReqId(usernameReq.getReqId());
            rsp.setUserId(String.valueOf(user.getId()));
            return rsp;
        }

        @Override
        public boolean support(Object o) {
            if (o instanceof UsernameReq) {
                return true;
            }
            return false;
        }
    }


}
