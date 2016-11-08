package com.sample.core.model.dto;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by andongxu on 9/10/16.
 */
public class QueryRsp<D> extends Rsp {

    /**
     * 第几页
     */
    protected Integer pageNumber;

    /**
     * 每页数据条数
     */
    protected Integer pageSize;

    /**
     * 总记录数
     */
    protected Integer totalPages;

    /**
     * 总记录数据
     */
    protected Long totalElements;

    /**
     * 查询结果
     */
    protected D data;

    public QueryRsp() {
    }

    public QueryRsp(boolean isSuccess) {
        super.isSuccess = isSuccess;
    }


    public Integer getPageNumber() {
        return pageNumber;
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

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString() + "QueryRsp{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", date=" + data+
                '}';
    }
}
