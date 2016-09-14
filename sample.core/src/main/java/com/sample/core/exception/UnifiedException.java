package com.sample.core.exception;

import java.util.Map;

/**
 * Created by andongxu on 16-7-29.
 */
public class UnifiedException extends Exception {


    private ExceptionLevel level = ExceptionLevel.SLIGHT;

    private String system = "unknown";

    private String businessModule = "unknown";

    /**
     * 异常上下文，可以设置业务参数
     */
    private Map<String, Object> context;

    private String errorCode = "0";

    private String errorMessage = "";

    /**
     * 异常是否处理了
     */
    private boolean isHandler = false;

    /**
     *
     * @param level 异常级别
     * @see com.sample.core.exception.ExceptionLevel
     * @param errorCode 错误代码
     * @param errorMessage  错误描述
     * @param businessModule    业务模块
     * @param context 上下文，可设置业务参数
     * @param cause 那个异常引发这个异常
     */
    public UnifiedException(ExceptionLevel level, String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        super(cause);
        this.level = level;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.system = system;
        this.businessModule = businessModule;
        this.context = context;
    }

    public ExceptionLevel getLevel() {
        return level;
    }

    public void setLevel(ExceptionLevel level) {
        this.level = level;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBusinessModule() {
        return businessModule;
    }

    public void setBusinessModule(String businessModule) {
        this.businessModule = businessModule;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isHandler() {
        return isHandler;
    }

    public void setHandler(boolean handler) {
        isHandler = handler;
    }
}
