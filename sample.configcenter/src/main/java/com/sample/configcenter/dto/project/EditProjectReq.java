package com.sample.configcenter.dto.project;

import com.sample.core.model.dto.Req;

import javax.validation.constraints.Pattern;

/**
 * Created by andongxu on 16-11-8.
 */
public class EditProjectReq extends Req {

//    @Pattern(regexp = "\\d{1,8}", message = "格式:\\d{1,8}")
    private Long id;

    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,32}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,5}")
    private String name;

    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,128}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,128}")
    private String content;

    private Long versionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }
}
