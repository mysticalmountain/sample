package com.sample.core.model.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by andongxu on 9/10/16.
 */
public class QueryReqDto extends ReqDto {

    /**
     * 第几页,如果值大于0则分页查询，否则查询所有记录
     */
    @Range(min = 0, max = Integer.MAX_VALUE)
    private Integer pageNumber;

    /**
     * 每页数据条数
     */
    @Range(min = 0, max = Integer.MAX_VALUE)
    private Integer pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
