package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andongxu on 16-9-1.
 */
@Transactional
@Commit
public class ValidateChannelHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("validateService")
    private ISampleService<ValidateReq, ValidateRsp> sampleService;

    @Test
    public void hasPermission() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setOwner("WX");
        validateReq.setServiceCode("1006");
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertTrue(validateRsp.isSuccess());
    }

    @Test
    public void hasNotPermission() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setOwner("WY");
        validateReq.setServiceCode("1006");
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertFalse(validateRsp.isSuccess());
    }

    @Test
    public void channelNotExists() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setOwner("AA");
        validateReq.setServiceCode("1006");
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertFalse(validateRsp.isSuccess());
    }
}
