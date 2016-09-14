package com.sample.core.log;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.api.ILogManager;
import com.sample.core.log.handler.LogHandlerChain;
import com.sample.core.model.dto.ReqDto;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-22.
 */
@Component
public class LogManager<P> implements ILogManager<P> {

    @Autowired
    private LogHandlerChain chain;

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    public void write(LogInfo<P> logInfo) throws UnifiedException {
        chain.handle(logInfo);
    }
}
