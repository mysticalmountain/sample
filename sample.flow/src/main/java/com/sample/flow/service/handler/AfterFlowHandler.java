package com.sample.flow.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractAfterVoidServiceHandler;
import com.sample.core.service.handler.Priority;
import com.sample.core.utils.ObjectSerializeUtil;
import com.sample.flow.model.ReqPK;
import com.sample.flow.model.ReqRspFlow;
import com.sample.flow.repository.ReqRspFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by andongxu on 9/26/16.
 */
@Component
@Priority(10)
public class AfterFlowHandler<I extends Rsp> extends AbstractAfterVoidServiceHandler<I> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private ReqRspFlowRepository reqRspFlowRepository;

    @Override
    public Void doHandle(Rsp rsp, Service service) throws UnifiedException {
        ReqPK reqPK = new ReqPK();
        reqPK.setRequestId(rsp.getReqId());
        reqPK.setServiceCode(service.code());
        ReqRspFlow reqRspFlow = reqRspFlowRepository.findOne(reqPK);
        try {
            reqRspFlow.setRspMsg(ObjectSerializeUtil.serializeObject(rsp));
            reqRspFlowRepository.save(reqRspFlow);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionLevel.COMMON, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
        return null;
    }

    @Override
    public boolean support(Object o) {
        return true;
    }
}
