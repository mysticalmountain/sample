package com.sample.test;

import java.io.Serializable;

/**
 * Created by andongxu on 9/23/16.
 */
public class Bean implements Serializable{

    private int id;

    private String name;

    public Bean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
