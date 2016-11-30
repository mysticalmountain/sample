package com.sample.user.repository;

import com.sample.permission.model.Role;
import com.sample.permission.repository.RoleRepository;
import com.sample.user.BaseTest;
import com.sample.user.model.Authority;
import com.sample.user.model.User;
import com.sample.user.model.enums.AuthType;
import com.sample.user.model.enums.UserType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-11-17.
 */
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save1() {
        User user = new User();
        user.setName("zhao");
        user.setUsername("zhao");
        user.setUserType(UserType.PERSONAL);
        userRepository.save(user);
    }

    @Test
    public void save2() {
        User user = new User();
        user.setName("user22");
        user.setUsername("user2");
        user.setUserType(UserType.PERSONAL);
        Authority authority = new Authority();
        authority.setAccountNo("user2");
        authority.setPassword("123456");
        authority.setAuthType(AuthType.USERNAME);
        Set<Authority> authoritySet = new HashSet<Authority>();
        authoritySet.add(authority);
        user.setAuthtications(authoritySet);
        userRepository.save(user);

    }

    @Test
    public void save3() {
        User user = userRepository.findOne(Long.valueOf(10000007));
        Authority authority = new Authority();
        authority.setAccountNo("user3");
        authority.setPassword("123456");
        authority.setAuthType(AuthType.USERNAME);
        Set<Authority> authoritySet = new HashSet<Authority>();
        authoritySet.add(authority);
        user.setAuthtications(authoritySet);
        userRepository.save(user);
    }
    @Test
    public void save4() {
        User user = userRepository.findOne(Long.valueOf(10000007));
        Authority authority = new Authority();
        authority.setAccountNo("user3");
        authority.setPassword("123456");
        authority.setAuthType(AuthType.USERNAME);
        authority.setUser(user);
        authorityRepository.save(authority);
    }

    @Test
    public void save5() {
        User user = userRepository.findOne(Long.valueOf(10000007));

    }

    @Test
    public void save6() {
        User user = userRepository.findOne(Long.valueOf(10000007));
        Role role = roleRepository.findOne(Long.valueOf(10000002));
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Test
    public void save7() {
        Role role = new Role();
        role.setName("管理员");
//        Set<Role> roles = new HashSet<Role>();
        roleRepository.save(role);
    }
}
