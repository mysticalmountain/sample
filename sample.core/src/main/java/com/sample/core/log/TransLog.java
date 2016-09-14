package com.sample.core.log;

import java.lang.annotation.*;

/**
 * Created by andongxu on 16-8-19.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransLog {

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
