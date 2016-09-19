package com.sample.core.service;

import java.lang.annotation.*;

/**
 * Created by andongxu on 16-8-31.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    /**
     * 代码
     * @return
     */
    String code() default "";

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
     * 是否记录日志
     */
    boolean isWriteLog() default true;

    /**
     * 是否验证请求数据
     */
    boolean isValidateReq() default false;

}
