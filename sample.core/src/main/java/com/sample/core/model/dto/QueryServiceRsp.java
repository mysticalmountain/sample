package com.sample.core.model.dto;

/**
 * Created by andongxu on 16-11-20.
 */
public class QueryServiceRsp {

    private String code;

    private String content;

    private String system;

    private String module;

    private boolean isWriteLog;

    private boolean isValidateReq;

    private boolean isIdempotent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isWriteLog() {
        return isWriteLog;
    }

    public void setWriteLog(boolean writeLog) {
        isWriteLog = writeLog;
    }

    public boolean isValidateReq() {
        return isValidateReq;
    }

    public void setValidateReq(boolean validateReq) {
        isValidateReq = validateReq;
    }

    public boolean isIdempotent() {
        return isIdempotent;
    }

    public void setIdempotent(boolean idempotent) {
        isIdempotent = idempotent;
    }
}
