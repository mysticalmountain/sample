package com.sample.boot;

import com.sample.core.CoreConfig;
import com.sample.permission.PermissionConfig;
import com.sample.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by andongxu on 16-8-25.
 */
@Import(value = {CoreConfig.class, WebConfig.class, PermissionConfig.class})
public class PermissionApp {

    public static void main(String [] args) {
        SpringApplication.run(PermissionApp.class, args);
    }

}
