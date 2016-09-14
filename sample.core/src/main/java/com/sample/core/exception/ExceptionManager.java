package com.sample.core.exception;

import com.sample.core.exception.handler.ExceptionHandlerChain;
import com.sample.core.exception.api.IExceptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 16-7-29.
 */
@Component
public class ExceptionManager implements IExceptionManager {

    private String systemCode;

    @Autowired
    private ExceptionHandlerChain chain;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void publish(ExceptionLevel level, String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        UnifiedException ue = new UnifiedException(level, errorCode, errorMessage, businessModule, context, throwable);
        this.accept(ue, gi);
    }

    @Override
    public void publish(UnifiedException ue) {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        accept(ue, gi);
    }

    @Override
    public void publishSerious(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SERIOUS, errorCode, errorMessage, businessModule, context, cause);
        accept(ue, gi);
    }

    @Override
    public void publishCommon(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.COMMON, errorCode, errorMessage, businessModule, context, cause);
        accept(ue, gi);
    }

    @Override
    public void publishSlight(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SLIGHT, errorCode, errorMessage, businessModule, context, cause);
        accept(ue, gi);
    }

    @Override
    public void publishAndThrow(UnifiedException ue) throws UnifiedException {
        GlobalInfo gi = new GlobalInfo(systemCode, taskExecutor);
        accept(ue, gi);
        throw ue;
    }

    protected void accept(UnifiedException ue, GlobalInfo gi) {
        if (!ue.isHandler()) {
            chain.handleException(ue, gi);
            ue.setHandler(true);
        }
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public ExceptionHandlerChain getChain() {
        return chain;
    }

    public void setChain(ExceptionHandlerChain chain) {
        this.chain = chain;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
