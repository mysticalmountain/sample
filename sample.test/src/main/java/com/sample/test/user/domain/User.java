package com.sample.test.user.domain;





import com.sample.test.user.BaseEntity;
import com.sample.test.user.Entity;

import javax.persistence.*;

/**
 * Created by andongxu on 16-6-6.
 */

@javax.persistence.Entity
@Table(name = "uc_user")
public class User extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String name;

//    @Transient
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
