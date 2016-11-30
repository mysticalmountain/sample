package com.sample.permission.repository;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
public class TreeUtil<T> {

    public void copy(Tree<T> source, Tree<T> target) {

    }

    public void each(Tree<T> tree) {
        Iterator<T> sons = tree.getSons().iterator();
        while (sons.hasNext()) {
            T t  = sons.next();
            each(new Tree<T>());
        }
    }

    @Test
    public void t1() {
       System.out.println("t1");
    }
}
