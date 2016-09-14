package com.sample.permission.model;

/**
 * Created by andongxu on 16-8-26.
 */
public enum  PermissionType {

    MENU(0, "菜单"),
    DATA(1, "数据"),
    FILE(2, "文件"),
    CHANNEL(3, "渠道");

    private int value;

    private String description;

    private PermissionType(int value, String description) {
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
