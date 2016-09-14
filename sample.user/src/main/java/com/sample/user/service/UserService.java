package com.sample.user.service;

import com.sample.user.model.dto.USC01001;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.model.dto.UsernameReq;

/**
 * Created by andongxu on 16-6-14.
 */
public interface UserService {

    public UserIdRsp register(USC01001 usc01001) throws Exception;

    public UserIdRsp getUserByUsername(UsernameReq usernameReq);

}
