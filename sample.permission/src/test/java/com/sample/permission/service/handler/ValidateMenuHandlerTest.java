package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import com.sample.permission.model.PermissionType;
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
public class ValidateMenuHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("validateService")
    private ISampleService<ValidateReq, ValidateRsp> sampleService;

    @Test
    public void hasPermission() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setServiceCode("1006");
        validateReq.setOwner("10000020");
        validateReq.setPermissionType(PermissionType.MENU);
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertTrue(validateRsp.isSuccess());
    }

    @Test
    public void hasNotPermission() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setServiceCode("1005");
        validateReq.setOwner("10000020");
        validateReq.setPermissionType(PermissionType.MENU);
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertFalse(validateRsp.isSuccess());
    }

    @Test
    public void menuNotExists() throws UnifiedException {
        ValidateReq validateReq = new ValidateReq();
        validateReq.setServiceCode("1006");
        validateReq.setOwner("10000021");
        validateReq.setPermissionType(PermissionType.MENU);
        ValidateRsp validateRsp = sampleService.service(validateReq);
        Assert.assertNotNull(validateRsp);
        Assert.assertFalse(validateRsp.isSuccess());
    }
}
