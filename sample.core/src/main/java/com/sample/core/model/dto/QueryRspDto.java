package com.sample.core.model.dto;

import javax.validation.constraints.Pattern;

/**
 * Created by andongxu on 9/10/16.
 */
public class QueryRspDto<D> extends RspDto {

    /**
     * 第几页
     */
    protected int pageNumber;

    /**
     * 每页数据条数
     */
    protected int pageSize;

    /**
     * 总记录数
     */
    protected int totalPages;

    /**
     * 总记录数据
     */
    protected long totalElements;

    /**
     * 查询结果
     */
    protected D date;

    public QueryRspDto() {
    }

    public QueryRspDto(boolean isSuccess) {
        super.isSuccess = isSuccess;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public D getDate() {
        return date;
    }

    public void setDate(D date) {
        this.date = date;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return super.toString() + "; QueryRspDto{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", date=" + date +
                '}';
    }
}
