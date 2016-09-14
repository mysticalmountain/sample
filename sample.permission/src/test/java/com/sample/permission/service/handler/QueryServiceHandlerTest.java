package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRspDto;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.QueryReq;
import com.sample.permission.dto.QueryRsp;
import com.sample.permission.dto.QueryServiceReqDto;
import com.sample.permission.dto.ServiceDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

/**
 * Created by andongxu on 16-9-2.
 */
public class QueryServiceHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("queryService")
    private ISampleService<QueryServiceReqDto, QueryRspDto> sampleService;

    @Test
    public void findAll() throws UnifiedException {
        QueryServiceReqDto queryServiceReqDto = new QueryServiceReqDto();
        QueryRspDto queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findByIdNotExists() throws UnifiedException {
        QueryServiceReqDto queryServiceReqDto = new QueryServiceReqDto();
        queryServiceReqDto.setId(Long.valueOf(1));
        QueryRspDto queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findById() throws UnifiedException {
        QueryServiceReqDto queryServiceReqDto = new QueryServiceReqDto();
        queryServiceReqDto.setId(Long.valueOf(10000007));
        QueryRspDto queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findByCode() throws UnifiedException {
        QueryServiceReqDto queryServiceReqDto = new QueryServiceReqDto();
        queryServiceReqDto.setCode("1001");
        QueryRspDto queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
    }

    @Test
    public void findPageable() throws UnifiedException {
        QueryServiceReqDto queryServiceReqDto = new QueryServiceReqDto();
        queryServiceReqDto.setPageNumber(-1);
        queryServiceReqDto.setPageSize(3);
        queryServiceReqDto.setReqId(UUID.randomUUID().toString());
        QueryRspDto queryRspDto = sampleService.service(queryServiceReqDto);
        Assert.assertNotNull(queryRspDto);
        Assert.assertFalse(queryRspDto.isSuccess());
    }
}


