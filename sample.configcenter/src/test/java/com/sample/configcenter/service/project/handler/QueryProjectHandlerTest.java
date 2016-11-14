package com.sample.configcenter.service.project.handler;

import com.sample.configcenter.BaseTest;
import com.sample.configcenter.dto.project.ProjectDto;
import com.sample.configcenter.dto.project.QueryProjectReq;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.ISampleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.UUID;

/**
 * Created by andongxu on 16-11-1.
 */
public class QueryProjectHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("queryProjectService")
    private ISampleService<QueryProjectReq, QueryRsp<List<ProjectDto>>> queryProjectService;


    @Test
    public void success() throws UnifiedException {
        QueryProjectReq queryProjectReq = new QueryProjectReq();
        queryProjectReq.setReqId(UUID.randomUUID().toString());
        QueryRsp<List<ProjectDto>> queryRsp = queryProjectService.service(queryProjectReq);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(queryRsp);
    }
}
