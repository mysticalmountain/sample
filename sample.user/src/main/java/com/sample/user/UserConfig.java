package com.sample.user;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andongxu on 16-11-16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.sample.user")
public class UserConfig {
}
