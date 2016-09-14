package com.sample.user.model;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-7.
 */
@Entity
@Table
public class Role extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", targetEntity = User.class)
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
