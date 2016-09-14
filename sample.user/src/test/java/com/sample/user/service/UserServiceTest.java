package com.sample.user.service;

import com.sample.user.model.dto.USC01001;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.model.enums.UserType;
import com.sample.user.repository.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-6-15.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception {
        USC01001 usc01001 = new USC01001();
        usc01001.setReqId("123");
        usc01001.setUsername("wu");
        usc01001.setUserType(UserType.MERCHANT);
        UserIdRsp userIdRsp =userService.register(usc01001);

        System.out.println("************************" + userIdRsp.getErrorMsg());

    }

    @Test
    public void tmp() {
        System.out.println(UserType.MERCHANT.getIndex());
    }
}
