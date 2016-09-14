package com.sample.core.exception;

/**
 * 异常级别
 *
 * Created by andongxu on 16-7-29.
 */
public enum ExceptionLevel {

    SERIOUS(1, "严重"),
    COMMON(2, "一般"),
    SLIGHT(3, "轻微");

    private int value;

    private String description;

    private ExceptionLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
