package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-8-30.
 */
public class BaseRsp implements Serializable {

    private boolean isSuccess;

    public BaseRsp() {
    }

    public BaseRsp(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
