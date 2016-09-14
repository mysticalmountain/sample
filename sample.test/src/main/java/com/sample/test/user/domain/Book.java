package com.sample.test.user.domain;

import com.sample.test.user.BaseEntity;

import javax.persistence.*;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class Book extends BaseEntity {

    @Column(unique = false, length = 16, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
