package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.*;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.core.service.handler.BeforeServiceHandlerChain;
import com.sample.permission.dto.QueryServiceReq;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-30.
 */
@Component
@com.sample.core.service.Service(code = "1003", isValidateReq = true, isIdempotent = true)
public class QueryServiceService extends AbstractSampleService<QueryServiceReq, QueryRsp> {

    @Autowired
    private ServiceHandlerChain<QueryServiceReq, QueryRsp> chain;
    @Autowired
    private BeforeServiceHandlerChain voidChain;

    @Override
    public QueryRsp doService(QueryServiceReq queryServiceReq) throws UnifiedException {
        Service service = this.getClass().getAnnotation(Service.class);
        voidChain.handle(queryServiceReq, service);
        return chain.handle(queryServiceReq, service);
    }

    @Override
    public QueryRsp captureException(QueryServiceReq queryServiceReq, UnifiedException ue) throws UnifiedException {
        try {
            QueryRsp queryRsp = new QueryRsp();
            BeanUtils.copyProperties(queryRsp, queryServiceReq);
            queryRsp.setSuccess(false);
            queryRsp.setErrorCode(ue.getErrorCode());
            queryRsp.setErrorMsg(ue.getErrorMessage());
            return queryRsp;
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
    }
}
