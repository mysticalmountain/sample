package com.sample.configcenter.model;

import com.sample.core.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by andongxu on 16-11-29.
 */
@javax.persistence.Entity
public class Val extends Entity {
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

    private String val;

    @ManyToOne(targetEntity = Kei.class)
    @JoinColumn(name = "key_id", referencedColumnName = "id")
    private Kei kei;

    @ManyToOne(targetEntity = Env.class)
    @JoinColumn(name = "env_id", referencedColumnName = "id")
    private Env env;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Kei getKei() {
        return kei;
    }

    public void setKei(Kei kei) {
        this.kei = kei;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }
}
