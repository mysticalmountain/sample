package com.sample.permission;

import com.sample.core.CoreConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by andongxu on 9/14/16.
 */
@EnableAutoConfiguration
@ComponentScan("com.sample.permission")
@PropertySource("classpath:application.properties")
public class PermissionConfig {
}
