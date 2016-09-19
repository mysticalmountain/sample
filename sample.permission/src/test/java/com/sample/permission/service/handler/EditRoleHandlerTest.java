package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditChannelReq;
import com.sample.permission.dto.EditRoleReq;
import com.sample.permission.dto.KeyValue;
import com.sample.permission.model.Operate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-8-31.
 */
@Transactional
@Commit
public class EditRoleHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("editServiceService")
    private ISampleService<EditRoleReq, BaseRsp> sampleService;

    @Test
    public void success() throws UnifiedException {
        EditRoleReq<Long, Operate> editRoleReq = new EditRoleReq<Long, Operate>();
        editRoleReq.setOwner("10000000");
        Set<KeyValue<Long, Operate>> menuOperateSet = new HashSet<KeyValue<Long, Operate>>();
        KeyValue<Long, Operate> menuOperate0 = new KeyValue<Long, Operate>();
        menuOperate0.setK(Long.valueOf(10000018));
        menuOperate0.setV(Operate.WRITE);
        menuOperateSet.add(menuOperate0);
        editRoleReq.setKeyValues(menuOperateSet);
        BaseRsp baseRsp = sampleService.service(editRoleReq);
        Assert.assertNotNull(baseRsp);
        Assert.assertTrue(baseRsp.isSuccess());
    }
}
