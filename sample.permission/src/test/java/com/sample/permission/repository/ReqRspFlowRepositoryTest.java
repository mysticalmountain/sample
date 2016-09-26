package com.sample.permission.repository;

import com.sample.flow.model.ReqRspFlow;
import com.sample.flow.model.ReqPK;
import com.sample.flow.repository.ReqRspFlowRepository;
import com.sample.permission.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by andongxu on 9/20/16.
 */
public class ReqRspFlowRepositoryTest extends BaseTest {

    @Autowired
    private ReqRspFlowRepository idempotentFlowRepository;

    @Test
    public void save() throws IOException {
        ReqRspFlow reqRspFlow = new ReqRspFlow();
        ReqPK idempotentPK = new ReqPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        reqRspFlow.setId(idempotentPK);
        reqRspFlow.setReqMsg(SerializeUtil.serializeObject(reqRspFlow));
        idempotentFlowRepository.save(reqRspFlow);
    }

    @Test
    public void findOne() throws IOException, ClassNotFoundException {
        ReqPK idempotentPK = new ReqPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        ReqRspFlow reqRspFlow = idempotentFlowRepository.findOne(idempotentPK);
        Assert.assertNotNull(reqRspFlow);
        byte[] breq = reqRspFlow.getReqMsg();
        ReqRspFlow rrf = SerializeUtil.deserializeObject(breq, ReqRspFlow.class);
        Assert.assertNotNull(rrf);

    }

    @Test
    public void update() {
        ReqPK idempotentPK = new ReqPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        ReqRspFlow idempotentFlow = idempotentFlowRepository.findOne(idempotentPK);
        Assert.assertNotNull(idempotentFlow);
        idempotentFlowRepository.save(idempotentFlow);
    }
}
