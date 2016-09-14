package com.sample.test.user.domain;

import com.sample.test.user.BaseEntity;

import javax.persistence.*;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class AuthorProfile extends BaseEntity {

    @OneToOne
//    @PrimaryKeyJoinColumn(name = "author_id")
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Column
    private String address;

    @Column
    private String address2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zip;

    @Column
    private String phone;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
