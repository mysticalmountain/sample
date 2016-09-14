package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-8-30.
 */
public class KeyValue<K, V> implements Serializable {

    private K k;

    private V v;


    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
