package com.sample.user.service.impl;

import com.sample.core.validator.Validator;
import com.sample.user.model.User;
import com.sample.user.model.dto.USC01001;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.model.dto.UsernameReq;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-6-15.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Validator
    @Override
    public UserIdRsp register(USC01001 usc01001) throws Exception {
        User user = userRepository.findByUsername(usc01001.getUsername());
        if (user != null) {
            throw new Exception("");
//            return null;
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
    public UserIdRsp getUserByUsername(UsernameReq usernameReq) {

        User user = userRepository.findByUsername(usernameReq.getUsername());
        if (user == null) {
            return  null;
        }
        UserIdRsp rsp = new UserIdRsp();
        rsp.setErrorCode("000000");
        rsp.setReqId(usernameReq.getReqId());
        rsp.setUserId(String.valueOf(user.getId()));
        return rsp;
    }
}
