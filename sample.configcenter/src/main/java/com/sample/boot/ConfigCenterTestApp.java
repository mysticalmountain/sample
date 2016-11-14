package com.sample.boot;

import com.sample.configcenter.ConfigCenterConfig;
import com.sample.core.CoreConfig;
import com.sample.flow.FlowConfig;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 9/13/16.
 */
@Import(value = {CoreConfig.class, ConfigCenterConfig.class, FlowConfig.class})
public class ConfigCenterTestApp {

}
