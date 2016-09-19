package com.sample.core.model.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-6-15.
 */
public class Rsp implements Serializable {

    protected String reqId;

    protected String errorCode;

    protected String errorMsg;

    protected boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Rsp{" +
                "reqId='" + reqId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
