package com.sample.boot;

import com.sample.core.CoreConfig;
import com.sample.flow.FlowConfig;
import com.sample.permission.PermissionConfig;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 9/13/16.
 */
@Import(value = {PermissionConfig.class, CoreConfig.class, FlowConfig.class})
public class PermissionTestApp {

}
