package com.sample.web.controller;

import com.sample.core.exception.UnifiedException;
import com.sample.web.dispatch.ServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andongxu on 9/11/16.
 */
@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @RequestMapping(value = "{code}")
    public @ResponseBody String service(@PathVariable String code, @RequestBody String body) throws Exception {
        return serviceDispatcher.execute(code, body);
    }

    @ExceptionHandler(UnifiedException.class)
    public @ResponseBody String handleIOException(UnifiedException ex) {
        return "unified exception";
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleIOException(Exception ex) {
        return "exception";
    }
}
