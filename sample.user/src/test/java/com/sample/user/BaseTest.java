package com.sample.user;

import com.sample.core.CoreConfig;
import com.sample.permission.PermissionConfig;
import com.sample.web.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by andongxu on 16-6-12.
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {UserConfig.class})
@PropertySource("classpath:application.properties")
@Import({WebConfig.class, CoreConfig.class, PermissionConfig.class})
public class BaseTest {

}
