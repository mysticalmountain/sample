package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-8-29.
 */
public class ValidateRsp implements Serializable {

    private boolean isSuccess;

    public ValidateRsp() {

    }

    public ValidateRsp(boolean isSuccess) {
         this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
