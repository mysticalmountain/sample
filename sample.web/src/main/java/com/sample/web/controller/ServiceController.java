package com.sample.web.controller;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.web.dispatch.ServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andongxu on 9/11/16.
 */
@Controller
@RequestMapping(value = "/service", produces = "application/json; charset=UTF-8")
public class ServiceController {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @RequestMapping(value = "{code}")
    public
    @ResponseBody
    String service(@PathVariable String code, @RequestBody String body) throws Exception {
        return serviceDispatcher.execute(code, body);
    }

    @ExceptionHandler(UnifiedException.class)
    public @ResponseBody String handleIOException(UnifiedException ue) {
        log.error(ue.getMessage(), ue);
        return "{\"errorCode\":" + ue.getErrorCode() + ",\"errorMsg\":" + ue.getErrorMessage() + ",\"success\":false}";
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleIOException(Exception e) {
        log.error(e.getMessage(), e);
        String message = "";
        if (e instanceof HttpMessageNotReadableException) {
            message = "request data is not readable";
        }else {
            message = "unknown error";
        }
        return "{\"errorCode\":" + "999999" + ",\"errorMsg\":" + "\"" + message + "\"" + ",\"success\":false}";
    }
}
