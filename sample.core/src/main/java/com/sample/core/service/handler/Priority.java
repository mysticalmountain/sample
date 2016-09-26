package com.sample.core.service.handler;

import java.lang.annotation.*;

/**
 * 值越小优先级越高
 * Created by andongxu on 9/21/16.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Priority {

    int value() default Integer.MAX_VALUE;
}
