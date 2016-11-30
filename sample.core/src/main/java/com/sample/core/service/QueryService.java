package com.sample.core.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.*;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-20.
 */
//@Component
//@Service(code = "queryService")
public class QueryService extends AbstractSampleService<Req, GenericSetRsp<QueryServiceRsp>> {

    @Autowired(required = false)
    private List<AbstractSampleService> abstractSampleServiceList;

    @Override
    public GenericSetRsp<QueryServiceRsp> doService(Req req) throws UnifiedException {
        Set<QueryServiceRsp> queryServiceRsps = new HashSet<QueryServiceRsp>();
        for (int i = 0; i < abstractSampleServiceList.size(); i++) {
            Object sampleService = abstractSampleServiceList.get(i);
            Service service = null;
            try {
                Method getTargetSource = sampleService.getClass().getMethod("getTargetSource");
                TargetSource ts = (TargetSource) getTargetSource.invoke(sampleService);
                Class targetClass = ts.getTargetClass();
                service = (Service) targetClass.getAnnotation(Service.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            QueryServiceRsp queryServiceRsp = new QueryServiceRsp();
            queryServiceRsp.setCode(service.code());
            queryServiceRsp.setContent(service.name());
            queryServiceRsp.setSystem(service.system());
            queryServiceRsp.setModule(service.module());
            queryServiceRsp.setValidateReq(service.isValidateReq());
            queryServiceRsp.setWriteLog(service.isWriteLog());
            queryServiceRsps.add(queryServiceRsp);
        }
        GenericSetRsp<QueryServiceRsp> genericSetRsp = new GenericSetRsp<QueryServiceRsp>();
        genericSetRsp.setData(queryServiceRsps);
        return genericSetRsp;
    }

    @Override
    public GenericSetRsp<QueryServiceRsp> captureException(Req req, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
