package com.sample.boot;

import com.sample.configcenter.ConfigCenterConfig;
import com.sample.core.CoreConfig;
import com.sample.flow.FlowConfig;
import com.sample.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 16-10-31.
 */
@Import(value = {CoreConfig.class, ConfigCenterConfig.class, FlowConfig.class, WebConfig.class})
public class ConfigCenterApp {
    public static void main(String [] args) {
        SpringApplication.run(ConfigCenterApp.class, args);
    }
}
