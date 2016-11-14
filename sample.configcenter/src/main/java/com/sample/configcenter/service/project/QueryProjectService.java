package com.sample.configcenter.service.project;

import com.sample.configcenter.dto.project.ProjectDto;
import com.sample.configcenter.dto.project.QueryProjectReq;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
@Service(isValidateReq = true, code = "1001")
public class QueryProjectService extends AbstractSampleService<QueryProjectReq, QueryRsp<List<ProjectDto>>> {

    @Autowired
    private ServiceHandlerChain<QueryProjectReq, QueryRsp<List<ProjectDto>>> chain;
    @Override
    public QueryRsp<List<ProjectDto>> doService(QueryProjectReq queryProjectReq) throws UnifiedException {
        return chain.handle(queryProjectReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public QueryRsp<List<ProjectDto>> captureException(QueryProjectReq queryProjectReq, UnifiedException ue) throws UnifiedException {
        QueryRsp queryRsp = new QueryRsp();
        queryRsp.setErrorCode(ue.getErrorCode());
        queryRsp.setErrorMsg(ue.getErrorMessage());
        queryRsp.setSuccess(false);
        return queryRsp;
    }
}
