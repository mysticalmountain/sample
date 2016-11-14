package com.sample.configcenter.dto.item;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-9.
 */
public class ItemDto implements Serializable{

    private Long id;

    private String kei;

    private String val;

    private String content;

    private Long projectId;

    private Long versionId;

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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
