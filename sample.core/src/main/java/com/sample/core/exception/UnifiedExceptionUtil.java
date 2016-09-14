package com.sample.core.exception;

import java.util.Map;

/**
 * Created by andongxu on 16-7-29.
 */
public class UnifiedExceptionUtil {

    public static void throwSlightException(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SLIGHT, errorCode, errorMessage, businessModule, context, cause);
    }

    public static void throwCommonException(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.COMMON, errorCode, errorMessage, businessModule, context, cause);
    }

    public static void throwSeriousException(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SERIOUS, errorCode, errorMessage, businessModule, context, cause);
    }

}
