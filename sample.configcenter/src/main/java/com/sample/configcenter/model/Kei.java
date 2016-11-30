package com.sample.configcenter.model;

import com.sample.core.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-10-31.
 */
@javax.persistence.Entity
public class Kei extends Entity {

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

    @Column(unique = true, length = 64)
    private String kei;

    @Column(length = 255)
    private String content;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne(targetEntity = Version.class)
    @JoinColumn(name = "version_id", referencedColumnName = "id")
    private Version version;

    @OneToMany(mappedBy = "kei", targetEntity = Val.class)
    private Set<Val> vals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKei() {
        return kei;
    }

    public void setKei(String kei) {
        this.kei = kei;
    }

//    public String getVal() {
//        return val;
//    }
//
//    public void setVal(String val) {
//        this.val = val;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Set<Val> getVals() {
        return vals;
    }

    public void setVals(Set<Val> vals) {
        this.vals = vals;
    }
}
