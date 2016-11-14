package com.sample.core.model.dto;

/**
 * Created by andongxu on 16-11-9.
 */
public class GenericReq<T> extends Req {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
