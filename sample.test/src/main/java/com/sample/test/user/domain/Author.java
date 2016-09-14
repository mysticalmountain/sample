package com.sample.test.user.domain;

import com.sample.test.user.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class Author extends BaseEntity{

    @Column(unique = false, length = 16, nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", targetEntity = Book.class)
    private Set<Book> books;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AuthorProfile authorProfile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public AuthorProfile getAuthorProfile() {
        return authorProfile;
    }

    public void setAuthorProfile(AuthorProfile authorProfile) {
        this.authorProfile = authorProfile;
    }
}
