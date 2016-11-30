package com.sample.user.model;

import com.sample.permission.model.Role;
import com.sample.user.model.enums.UserType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-6.
 */

@Entity
@Table
public class User extends BaseEntity {

    @Column(length = 64)
    private String name;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user", targetEntity = Authority.class, cascade = CascadeType.PERSIST)
    private Set<Authority> authtications;

    @OneToOne(mappedBy = "user", targetEntity = Profile.class)
//    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthtications() {
        return authtications;
    }

    public void setAuthtications(Set<Authority> authtications) {
        this.authtications = authtications;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
