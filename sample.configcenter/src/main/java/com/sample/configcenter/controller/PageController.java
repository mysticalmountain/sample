package com.sample.configcenter.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by andongxu on 16-11-1.
 */
@Controller
public class PageController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }
}
