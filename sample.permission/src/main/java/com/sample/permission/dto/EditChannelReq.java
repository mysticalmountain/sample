package com.sample.permission.dto;

import com.sample.permission.model.Permission;
import com.sample.permission.model.PermissionType;

import java.io.Serializable;
import java.security.Key;
import java.util.Set;
import java.util.List;

/**
 * Created by andongxu on 16-8-30.
 */
public class EditChannelReq<K, V> implements Serializable {

    private String owner;

    private Set<KeyValue<K, V>> keyValues;


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<KeyValue<K, V>> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(Set<KeyValue<K, V>> keyValues) {
        this.keyValues = keyValues;
    }

    @Override
    public String toString() {
        return "EditChannelReq{" +
                "owner='" + owner + '\'' +
                ", keyValues=" + keyValues +
                '}';
    }
}
