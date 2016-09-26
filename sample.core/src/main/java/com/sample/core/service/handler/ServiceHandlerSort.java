package com.sample.core.service.handler;

import java.util.Comparator;

/**
 * Created by andongxu on 9/21/16.
 */
public class ServiceHandlerSort implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Priority o1Priority = o1.getClass().getAnnotation(Priority.class);
        Priority o2Priority = o2.getClass().getAnnotation(Priority.class);
        if (o1Priority.value() - o2Priority.value() < 0) {
            return -1;
        } else if (o1Priority.value() - o2Priority.value() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
