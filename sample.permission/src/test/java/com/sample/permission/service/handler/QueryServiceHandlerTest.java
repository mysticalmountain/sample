package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.QueryServiceReq;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by andongxu on 16-9-2.
 */
public class QueryServiceHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("queryServiceService")
    private ISampleService<QueryServiceReq, QueryRsp> sampleService;

    @Test
    public void findAll() throws UnifiedException {
        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setReqId(UUID.randomUUID().toString());
        QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(queryRspDto);
        Assert.assertTrue(queryRspDto.isSuccess());
    }

    @Test
    public void findByIdNotExists() throws UnifiedException {
        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setId(Long.valueOf(1));
        QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findById() throws UnifiedException {
        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setId(Long.valueOf(10000007));
        QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findByCode() throws UnifiedException {
        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setCode("1001");
        QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findPageable() throws UnifiedException {
        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setPageNumber(-1);
        queryServiceReqDto.setPageSize(3);
        queryServiceReqDto.setReqId(UUID.randomUUID().toString());
        QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
        Assert.assertFalse(queryRspDto.isSuccess());
    }
}


