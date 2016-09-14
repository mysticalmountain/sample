package com.sample.core.exception;

import java.util.Map;

/**
 * Created by andongxu on 16-8-1.
 */
public class TransactionException extends UnifiedException {
    /**
     * @param level          异常级别
     * @param errorCode      错误代码
     * @param errorMessage   错误描述
     * @param businessModule 业务模块
     * @param context        上下文，可设置业务参数
     * @param cause          那个异常引发这个异常
     * @see ExceptionLevel
     */
    public TransactionException(ExceptionLevel level, String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) {
        super(level, errorCode, errorMessage, businessModule, context, cause);
    }
}
