package com.sample.configcenter;

import com.sample.boot.ConfigCenterApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by andongxu on 16-6-14.
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ConfigCenterApp.class})
public class BaseTest {
}
