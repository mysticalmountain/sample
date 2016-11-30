package com.sample.permission.repository;

import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
public class Tree<T> {

    public T parent;

    public Set<T> sons;

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public Set<T> getSons() {
        return sons;
    }

    public void setSons(Set<T> sons) {
        this.sons = sons;
    }
}
