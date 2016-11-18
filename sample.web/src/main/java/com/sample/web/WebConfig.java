package com.sample.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by andongxu on 9/11/16.
 */
@Configuration
@EnableSpringHttpSession
@ComponentScan("com.sample.web")
@PropertySource("classpath:application.properties")
public class WebConfig {

    @Bean
    public SessionRepository sessionRepository() {
        return new MapSessionRepository();
    }

}
