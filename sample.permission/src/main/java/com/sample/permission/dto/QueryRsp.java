package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-9-2.
 */
public class QueryRsp<D> implements Serializable {

    private boolean isSuccess;

    private D data;

    public QueryRsp() {
    }

    public QueryRsp(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
