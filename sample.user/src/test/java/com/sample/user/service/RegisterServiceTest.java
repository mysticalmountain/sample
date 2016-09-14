package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import com.sample.user.model.dto.USC01001;
import com.sample.user.model.dto.UserIdRsp;
import com.sample.user.model.enums.UserType;
import com.sample.user.repository.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 16-8-18.
 */
public class RegisterServiceTest extends BaseTest {

    @Autowired
    @Qualifier("registerService")
    private ISampleService<USC01001, UserIdRsp> registerService;

    @Test
    public void register() throws UnifiedException {
        USC01001 usc01001 = new USC01001();
        usc01001.setReqId("123");
        usc01001.setUsername("wu");
        usc01001.setUserType(UserType.MERCHANT);
        UserIdRsp userIdRsp =registerService.service(usc01001);
        Assert.assertNotNull(userIdRsp);

        System.out.println("************************" + userIdRsp.getErrorMsg());
    }

    @Test
    public void registerDataFormatError() throws UnifiedException {

        USC01001 usc01001 = new USC01001();
//        usc01001.setReqId("123");
        usc01001.setUsername("wu");
        usc01001.setUserType(UserType.MERCHANT);
        UserIdRsp userIdRsp =registerService.service(usc01001);
        Assert.assertNotNull(userIdRsp);
        System.out.println(userIdRsp.getErrorMsg());
//        System.out.println("************************");
//        userIdRsp = registerService.service(usc01001);
//
//        System.out.println("************************");
//        System.out.println(userIdRsp.getErrorMsg());

    }
}
