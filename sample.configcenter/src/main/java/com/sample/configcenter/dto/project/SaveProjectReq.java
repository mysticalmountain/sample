package com.sample.configcenter.dto.project;

import com.sample.core.model.dto.Req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by andongxu on 16-11-7.
 */
public class SaveProjectReq extends Req {

    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,32}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,5}")
    @NotNull(message = "not null")
    private String name;

    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,128}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,128}")
    @NotNull(message = "not null")
    private String content;

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
}
