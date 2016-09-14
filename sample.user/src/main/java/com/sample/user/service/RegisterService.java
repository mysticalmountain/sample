package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.log.TransLog;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.validator.Validator;
import com.sample.user.model.User;
import com.sample.user.model.dto.USC01001;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-17.
 */
@Component
@Validator
public class RegisterService extends AbstractSampleService<USC01001, UserIdRsp> {

    @Autowired
    private UserRepository userRepository;

    @Validator(value = "aaaa")
    @TransLog
    @Override
    public UserIdRsp doService(USC01001 usc01001) throws UnifiedException {
        User user = userRepository.findByUsername(usc01001.getUsername());
        if (user != null) {
            UnifiedExceptionUtil.throwCommonException("999", "用户以存在", "user", null, null);
        }
        user = new User();
        user.setUsername(usc01001.getUsername());
        user.setUserType(usc01001.getUserType());
        user = userRepository.save(user);
        UserIdRsp rsp = new UserIdRsp();
        rsp.setErrorCode("000000");
        rsp.setReqId(usc01001.getReqId());
        rsp.setUserId(String.valueOf(user.getId()));
        return rsp;
    }

    @Override
    public UserIdRsp captureException(USC01001 usc01001, UnifiedException ue) throws UnifiedException {
        UserIdRsp rsp = new UserIdRsp();
        rsp.setReqId(usc01001.getReqId());
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        return rsp;
    }
}
