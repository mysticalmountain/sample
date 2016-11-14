package com.sample.core.model.dto;

import java.util.List;

/**
 * Created by andongxu on 16-11-9.
 */
public class GenericListReq<T> extends Req {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
