package com.sample.permission.repository;

import com.sample.idempotent.model.IdempotentFlow;
import com.sample.idempotent.model.IdempotentPK;
import com.sample.idempotent.model.Status;
import com.sample.idempotent.repository.IdempotentFlowRepository;
import com.sample.permission.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 9/20/16.
 */
public class IdempotentFlowRepositoryTest extends BaseTest {

    @Autowired
    private IdempotentFlowRepository idempotentFlowRepository;

    @Test
    public void save() {
        IdempotentFlow idempotentFlow = new IdempotentFlow();
        IdempotentPK idempotentPK = new IdempotentPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        idempotentFlow.setId(idempotentPK);
        idempotentFlow.setStatus(Status.SUCCESS);
        idempotentFlowRepository.save(idempotentFlow);
    }

    @Test
    public void findOne() {
        IdempotentPK idempotentPK = new IdempotentPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        IdempotentFlow idempotentFlow = idempotentFlowRepository.findOne(idempotentPK);
        Assert.assertNotNull(idempotentFlow);
    }

    @Test
    public void update() {
        IdempotentPK idempotentPK = new IdempotentPK();
        idempotentPK.setServiceCode("1001");
        idempotentPK.setRequestId("1000002");
        IdempotentFlow idempotentFlow = idempotentFlowRepository.findOne(idempotentPK);
        Assert.assertNotNull(idempotentFlow);
        idempotentFlow.setStatus(Status.FAIL);
        idempotentFlowRepository.save(idempotentFlow);
    }
}
