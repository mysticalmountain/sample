package com.sample.core.log.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.log.LogInfo;
import org.springframework.stereotype.Component;
//import com.sample.core.log.LogInfo

/**
 * Created by andongxu on 16-8-22.
 */
@Component
public class ReqLogHandler<D> extends AbstractLogHandler<D> {

    private Log log = Log4jLog.getLog(this.getClass());

//    @Override
//    public void handle(LogInfo<D> logInfo, LogHandlerChain chain) throws UnifiedException {
//        Object[] args = (Object[]) logInfo.getData();
//        String argsStr = "";
//        int i = 1;
//        for (Object arg : args) {
//            if ("".equals(args)) {
//                argsStr += "Param " + i + "=" + arg.toString();
//            } else {
//                argsStr += "," + "Param " + i + "=" + arg.toString();
//            }
//            i++;
//        }
//        log.info("system=" + logInfo.getSystem() + ",module=" + logInfo.getModule() + ",trans=" + logInfo.getTrans() + ",--->args" + argsStr);
//    }

    @Override
    public void doHandle(LogInfo<D> logInfo) throws UnifiedException {
        Object[] args = (Object[]) logInfo.getData();
        String argsStr = "";
        int i = 1;
        for (Object arg : args) {
            if ("".equals(args)) {
                argsStr += "Param " + i + "=" + arg.toString();
            } else {
                argsStr += "," + "Param " + i + "=" + arg.toString();
            }
            i++;
        }
        log.info("system=" + logInfo.getSystem() + ",module=" + logInfo.getModule() + ",trans=" + logInfo.getTrans() + ",--->args" + argsStr);

    }

    @Override
    public boolean support(LogInfo<D> logInfo) {
        if (LogInfo.Direction.REQ.equals(logInfo.getDirection())) {
            return true;
        }
        return false;
    }
}
