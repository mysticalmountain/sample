package com.sample.user;

import com.sample.core.CoreConfig;
import com.sample.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by andongxu on 16-6-6.
 */
@SpringBootApplication
@Import(CoreConfig.class)
@PropertySource("classpath:application.properties")
public class UserApp {

    @Autowired
    private UserRepository userRepository;

    public static void main(String [] args) {
        SpringApplication.run(UserApp.class);
    }

}
