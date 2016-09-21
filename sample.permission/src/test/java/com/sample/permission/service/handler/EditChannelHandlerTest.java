package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.EditChannelReq;
import com.sample.permission.dto.BaseRsp;
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
 * Created by andongxu on 16-8-30.
 */
@Transactional
@Commit
public class EditChannelHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("editChannelService")
    private ISampleService<EditChannelReq, BaseRsp> addService;

    @Test
    public void success() throws UnifiedException {
        EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
        addReq.setReqId("1000001");
        addReq.setOwner("WY");
        Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
        KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
        serviceOperate0.setK("1001");
        serviceOperate0.setV(Operate.WRITE);
//        KeyValue<String, Operate> serviceOperate1 = new KeyValue<String, Operate>();
//        serviceOperate1.setK("1002");
//        serviceOperate1.setV(Operate.READ);
        serviceOperateSet.add(serviceOperate0);
//        serviceOperateSet.add(serviceOperate1);
        addReq.setKeyValues(serviceOperateSet);
        addService.service(addReq);

    }

    /**
     * 渠道已经有两个服务的权限，再增加两个服务
     * @throws UnifiedException
     */
    @Test
    public void addPermission()  {
        try {
            EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
            addReq.setOwner("WY");
            Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
            KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
            serviceOperate0.setK("1003");
            serviceOperate0.setV(Operate.WRITE);
            KeyValue<String, Operate> serviceOperate1 = new KeyValue<String, Operate>();
            serviceOperate1.setK("1004");
            serviceOperate1.setV(Operate.READ);
            KeyValue<String, Operate> serviceOperate2 = new KeyValue<String, Operate>();
            serviceOperate2.setK("1001");
            serviceOperate2.setV(Operate.READ);
            KeyValue<String, Operate> serviceOperate3 = new KeyValue<String, Operate>();
            serviceOperate3.setK("1002");
            serviceOperate3.setV(Operate.READ);
            serviceOperateSet.add(serviceOperate0);
            serviceOperateSet.add(serviceOperate1);
            serviceOperateSet.add(serviceOperate2);
            serviceOperateSet.add(serviceOperate3);
            addReq.setKeyValues(serviceOperateSet);
            addService.service(addReq);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 渠道已经有４个权限，减少至２个权限
     */
    @Test
    public void subtractPermission() {
        try {
            EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
            addReq.setOwner("WY");
            Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
            KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
            serviceOperate0.setK("1003");
            serviceOperate0.setV(Operate.WRITE);
            KeyValue<String, Operate> serviceOperate2 = new KeyValue<String, Operate>();
            serviceOperate2.setK("1001");
            serviceOperate2.setV(Operate.READ);
            serviceOperateSet.add(serviceOperate0);
            serviceOperateSet.add(serviceOperate2);
            addReq.setKeyValues(serviceOperateSet);
            addService.service(addReq);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addPermission_WX()  {
        try {
            EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
            addReq.setOwner("WX");
            Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
            KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
            serviceOperate0.setK("1005");
            serviceOperate0.setV(Operate.WRITE);
            KeyValue<String, Operate> serviceOperate1 = new KeyValue<String, Operate>();
            serviceOperate1.setK("1006");
            serviceOperate1.setV(Operate.READ);
            KeyValue<String, Operate> serviceOperate2 = new KeyValue<String, Operate>();
            serviceOperate2.setK("1001");
            serviceOperate2.setV(Operate.READ);
            serviceOperateSet.add(serviceOperate0);
            serviceOperateSet.add(serviceOperate1);
            serviceOperateSet.add(serviceOperate2);
            addReq.setKeyValues(serviceOperateSet);
            addService.service(addReq);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serviceCodeNotExists() {
        EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
        addReq.setOwner("WY");
        Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
        KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
        serviceOperate0.setK("1009");
        serviceOperate0.setV(Operate.WRITE);
        serviceOperateSet.add(serviceOperate0);
        addReq.setKeyValues(serviceOperateSet);
        try {
            BaseRsp baseRsp = addService.service(addReq);
            Assert.assertNotNull(baseRsp);
            Assert.assertFalse(baseRsp.isSuccess());
        } catch (UnifiedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void channelCodeNotExists() {
        EditChannelReq<String, Operate> addReq = new EditChannelReq<String,Operate>();
        addReq.setOwner("AA");
        Set<KeyValue<String, Operate>> serviceOperateSet = new HashSet<KeyValue<String, Operate>>();
        KeyValue<String, Operate> serviceOperate0 = new KeyValue<String, Operate>();
        serviceOperate0.setK("1001");
        serviceOperate0.setV(Operate.WRITE);
        serviceOperateSet.add(serviceOperate0);
        addReq.setKeyValues(serviceOperateSet);
        try {
            BaseRsp baseRsp = addService.service(addReq);
            Assert.assertNotNull(baseRsp);
            Assert.assertFalse(baseRsp.isSuccess());
        } catch (UnifiedException e) {
            e.printStackTrace();
        }
    }
}
