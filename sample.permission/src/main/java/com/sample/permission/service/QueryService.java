package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRspDto;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.ValidateRsp;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by andongxu on 16-9-1.
 */
@Component
@com.sample.core.service.Service(code = "1004")
public class QueryService<I, O> extends AbstractSampleService<I, O> {

    @Autowired
    private ServiceHandlerChain chain;

    @Override
    public O doService(I i) throws UnifiedException {
        return chain.handle(i);

    }

    @Override
    public O captureException(I i, UnifiedException ue) throws UnifiedException {
        QueryRspDto queryRspDto = new QueryRspDto();
        try {
            BeanUtils.copyProperties(queryRspDto, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        queryRspDto.setErrorMsg(ue.getErrorMessage());
        queryRspDto.setErrorCode(ue.getErrorCode());
        queryRspDto.setSuccess(false);
        return (O) queryRspDto;
    }
}
