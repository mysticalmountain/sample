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
import com.sample.core.utils.ThreadExecutorPool;
import com.sample.flow.model.ReqPK;
import com.sample.flow.model.ReqRspFlow;
import com.sample.flow.repository.ReqRspFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    @Autowired
    private ThreadExecutorPool threadExecutorPool;

    @Override
    public Void doHandle(final Rsp rsp, final Service service) throws UnifiedException {
//        threadExecutorPool.execute(new Runnable() {
//            @Override
//            public void run() {
        ReqPK reqPK = new ReqPK();
        reqPK.setRequestId(rsp.getReqId());
        reqPK.setServiceCode(service.code());
        ReqRspFlow reqRspFlow = reqRspFlowRepository.findOne(reqPK);
        if (reqRspFlow == null) {
            return null;
        }
        try {
            reqRspFlow.setRspMsg(ObjectSerializeUtil.serializeObject(rsp));
        } catch (IOException e) {
            log.error("对象序列化异常", e);
        }
        reqRspFlowRepository.save(reqRspFlow);
//            }
//        });
        return null;
    }

    @Override
    public boolean support(Object ... objs) {
        return true;
    }
}
