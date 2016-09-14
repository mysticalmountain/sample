package com.sample.test.repository;

import com.sample.test.BaseTest;
import com.sample.test.user.domain.Role;
import com.sample.test.user.domain.User;
import com.sample.test.user.repository.RoleRepository;
import com.sample.test.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-6-12.
 */


public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save() {
        for (int i = 2; i < 15; i++) {
            User user = new User();
            user.setName("wei-" + i);
            userRepository.save(user);
        }

    }

    @Test
    public void saveCascade() {
        User user = new User();
        Role role = new Role();
        user.setName("qian");
        role.setName("manager");
        user.setRole(role);
        userRepository.save(user);
    }

    @Test
    public void saveCascade2() {
        Role role = roleRepository.findOne(Long.valueOf(10000030));
        Assert.assertNotNull(role);
        Assert.assertNotNull(role.getId());
        System.out.println(role.getName() + role.getUsers());
//        User user = new User();
//        user.setName("sun");
//        user.setRole(role);
//        userRepository.save(user);
    }

    @Test
    public void deleteCascade() {
        userRepository.delete(Long.valueOf(10000020));
    }

    @Test
    public void delete() {
        for (int i = 1; i < 10; i++){
            userRepository.delete(Long.valueOf(i));
        }
    }


}
