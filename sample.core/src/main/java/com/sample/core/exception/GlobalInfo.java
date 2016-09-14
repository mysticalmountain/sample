package com.sample.core.exception;

import org.springframework.core.task.TaskExecutor;

/**
 * Created by andongxu on 16-7-29.
 */
public class GlobalInfo {

    private String systemCode;

    private boolean isLast;

    private TaskExecutor exceptionHandlerExecutor;

    public GlobalInfo(String systemCode, boolean isLast,TaskExecutor exceptionHandlerExecutor) {
        this.systemCode = systemCode;
        this.isLast = isLast;
        this.exceptionHandlerExecutor = exceptionHandlerExecutor;
    }

    public GlobalInfo (String systemCode, TaskExecutor exceptionHandlerExecutor) {
        this.systemCode = systemCode;
        this.exceptionHandlerExecutor = exceptionHandlerExecutor;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public TaskExecutor getExceptionHandlerExecutor() {
        return exceptionHandlerExecutor;
    }

    public void setExceptionHandlerExecutor(TaskExecutor exceptionHandlerExecutor) {
        this.exceptionHandlerExecutor = exceptionHandlerExecutor;
    }
}
