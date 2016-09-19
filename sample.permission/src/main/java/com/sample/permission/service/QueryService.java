package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        try {
            QueryRsp queryRspDto = new QueryRsp();
            BeanUtils.copyProperties(queryRspDto, i);
            queryRspDto.setErrorMsg(ue.getErrorMessage());
            queryRspDto.setErrorCode(ue.getErrorCode());
            queryRspDto.setSuccess(false);
            return (O) queryRspDto;
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
    }
}
