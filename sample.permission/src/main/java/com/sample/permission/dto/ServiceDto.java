package com.sample.permission.dto;

/**
 * Created by andongxu on 16-11-21.
 */
public class ServiceDto {

    private String code;

    private String content;

    private String system;

    private String module;

    private Boolean isWriteLog;

    private Boolean isValidateReq;

    private Boolean isIdempotent;

    private ResourceDto resourceDto;

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

    public Boolean isWriteLog() {
        return isWriteLog;
    }

    public void setWriteLog(Boolean writeLog) {
        isWriteLog = writeLog;
    }

    public Boolean isValidateReq() {
        return isValidateReq;
    }

    public void setValidateReq(Boolean validateReq) {
        isValidateReq = validateReq;
    }

    public Boolean isIdempotent() {
        return isIdempotent;
    }

    public void setIdempotent(Boolean idempotent) {
        isIdempotent = idempotent;
    }

    public Boolean getWriteLog() {
        return isWriteLog;
    }

    public Boolean getValidateReq() {
        return isValidateReq;
    }

    public Boolean getIdempotent() {
        return isIdempotent;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }
}
