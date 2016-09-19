package com.sample.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by andongxu on 9/11/16.
 */
@Component
@EnableWebMvc
@ComponentScan
@PropertySource("classpath:application.properties")
public class WebConfig {

}
