package com.sample.core.validator;

import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-6-22.
 */
public class FormatException extends UnifiedException {


    /**
     * @param level          异常级别
     * @param errorCode      错误代码
     * @param errorMessage   错误描述
     * @param businessModule 业务模块
     * @param context        上下文，可设置业务参数
     * @param cause          那个异常引发这个异常
     * @see ExceptionLevel
     */
    public FormatException(ExceptionLevel level, String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        super(level, errorCode, errorMessage, businessModule, context, cause);
    }

}
