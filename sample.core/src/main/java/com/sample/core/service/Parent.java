package com.sample.core.service;

import java.lang.annotation.*;

/**
 * Created by andongxu on 9/12/16.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parent {
}
