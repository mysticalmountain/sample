package com.sample.test;

import com.sample.test.user.domain.User;
import com.sample.test.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by andongxu on 16-6-6.
 */
@SpringBootApplication
public class Application {


    public static void main(String [] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        String [] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("---->" + name);
        }
    }
}
