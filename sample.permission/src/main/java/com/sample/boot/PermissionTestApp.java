package com.sample.boot;

import com.sample.core.CoreConfig;
import com.sample.permission.PermissionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by andongxu on 9/13/16.
 */
@Import(value = {PermissionConfig.class, CoreConfig.class})
public class PermissionTestApp {

}
