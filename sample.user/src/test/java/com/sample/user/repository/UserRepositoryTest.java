package com.sample.user.repository;

import com.sample.user.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-6-14.
 */
public class UserRepositoryTest  extends BaseTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save1() {
        User user = new User();
        user.setName("yang");
        userRepository.save(user);
    }
}
