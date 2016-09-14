package com.sample.core.exception.api;

import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-7-29.
 */
public interface IExceptionManager {

    public void publish(ExceptionLevel level, String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable throwable);

    public void publish(UnifiedException ue);

    public void publishSerious(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause);

    public void publishCommon(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause);

    public void publishSlight(String errorCode, String errorMessage, String businessModule, Map<String, Object> context, Throwable cause);

    public void publishAndThrow(UnifiedException ue) throws UnifiedException;
}
