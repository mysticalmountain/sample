package com.sample.test.user.domain;






import com.sample.test.user.BaseEntity;
import com.sample.test.user.Entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-7.
 */
@javax.persistence.Entity
@Table(name = "uc_role")
public class Role extends BaseEntity {


    @Column(unique = true, length = 16, nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<User> users;

//    @OneToMany
//    private Set<Authority> authtications;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

//    public Set<Authority> getAuthtications() {
//        return authtications;
//    }
//
//    public void setAuthtications(Set<Authority> authtications) {
//        this.authtications = authtications;
//    }
}
