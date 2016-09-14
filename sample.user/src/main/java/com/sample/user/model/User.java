package com.sample.user.model;

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

    @OneToMany(mappedBy = "user", targetEntity = Authtication.class)
    private Set<Authtication> authtications;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Profile profile;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authtication> getAuthtications() {
        return authtications;
    }

    public void setAuthtications(Set<Authtication> authtications) {
        this.authtications = authtications;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
