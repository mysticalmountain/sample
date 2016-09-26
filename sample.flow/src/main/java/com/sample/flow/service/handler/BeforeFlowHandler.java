package com.sample.flow.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.model.dto.Req;
import com.sample.core.service.handler.AbstractBeforeServiceHandler;
import com.sample.core.service.handler.Priority;
import com.sample.core.utils.ObjectSerializeUtil;
import com.sample.flow.FlowException;
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
@Priority(20)
public class BeforeFlowHandler<I extends Req, O> extends AbstractBeforeServiceHandler<I, O> {
    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private ReqRspFlowRepository reqRspFlowRepository;

    @Override
    public O doHandle(Req req, com.sample.core.service.Service service) throws UnifiedException {
        ReqPK reqPK = new ReqPK();
        reqPK.setRequestId(req.getReqId());
        reqPK.setServiceCode(service.code());
        if (service.isIdempotent()) {
            ReqRspFlow reqRspFlow = reqRspFlowRepository.findOne(reqPK);
            if (reqRspFlow != null) {
                byte[] rsp = reqRspFlow.getRspMsg();
                try {
                    return (O) ObjectSerializeUtil.deserializeObject(rsp, Object.class);
                } catch (Exception e) {
                    throw new FlowException(ExceptionLevel.COMMON, Constant.EXCEPTION_REPEAT_REQUEST[0], Constant.EXCEPTION_REPEAT_REQUEST[1], null, null, e);
                }
            } else {
                reqRspFlow = new ReqRspFlow();
                reqRspFlow.setId(reqPK);
                try {
                    reqRspFlow.setReqMsg(ObjectSerializeUtil.serializeObject(req));
                } catch (IOException e) {
                    throw new UnifiedException(ExceptionLevel.COMMON, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
                }
                reqRspFlowRepository.save(reqRspFlow);
            }
        }
        return null;
    }

    @Override
    public boolean support(Object o) {
        return true;
    }
}
