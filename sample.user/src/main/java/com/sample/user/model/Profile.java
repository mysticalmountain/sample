package com.sample.user.model;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class Profile extends com.sample.core.model.Entity {

    @Id
    private Long id;

    @Column(length = 2)
    private int age;

    @Column(length = 1)
    private int sex;

    @Column(length = 256)
    private String address;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
