package com.sample.core.model.dto;

/**
 * Created by andongxu on 16-11-11.
 */
public class GenericListRsp<T> extends Rsp {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
