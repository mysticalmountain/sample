package com.sample.core.validator;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Created by andongxu on 16-6-22.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validator {

    /**
     * 系统
     * @return
     */
    String system() default "";

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 交易
     * @return
     */
    String trans() default "";

    /**
     * 日志内容
     * @return
     */
    String value() default "";

}
