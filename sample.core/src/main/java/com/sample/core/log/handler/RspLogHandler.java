package com.sample.core.log.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.log.LogInfo;
import com.sample.core.log.api.ILogManager;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/10/16.
 */
@Component
public class RspLogHandler<D> extends AbstractLogHandler<D> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    public void doHandle(LogInfo<D> logInfo) throws UnifiedException {
        log.info("system=" + logInfo.getSystem() + ",module=" + logInfo.getModule() + ",trans=" + logInfo.getTrans() + ",--->datas=" + logInfo.getData());
    }

    @Override
    public boolean support(LogInfo<D> logInfo) {
        if (LogInfo.Direction.RSP.equals(logInfo.getDirection())) {
            return true;
        }
        return false;
    }
}
