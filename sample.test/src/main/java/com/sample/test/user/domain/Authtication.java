package com.sample.test.user.domain;

import com.sample.test.user.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-13.
 */
@Entity
@Table(name = "uc_authtication")
public class Authtication extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String name;

//    @OneToMany
//    private Set<User> users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
