package com.sample.configcenter.model;

import com.sample.core.model.Entity;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-10-31.
 */
@javax.persistence.Entity
@Table
public class Project extends Entity {

    @Id
    @GenericGenerator(name = "id", strategy = "enhanced-table",
            parameters = {
                    @Parameter(name = "table_name", value = "seq_id"),
                    @Parameter(name = "value_column_name", value = "next"),
                    @Parameter(name = "segment_column_name", value = "segment_name"),
                    @Parameter(name = "segment_value", value = "seq_id"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id")
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 255)
    private String content;

    @OneToMany(targetEntity = Version.class)
    @JoinColumn(name = "id")
    private Set<Version> versions;

    @OneToMany(targetEntity = Item.class)
    @JoinColumn(name = "id")
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Version> getVersions() {
        return versions;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void setVersions(Set<Version> versions) {
        this.versions = versions;
    }
}
