package com.sample.permission.dto;

import com.sample.core.model.dto.Rsp;

/**
 * Created by andongxu on 16-8-30.
 */
public class BaseRsp extends Rsp {

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
