package com.sample.permission.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
public class MenuDto {

    private Long id;

    private String name;

    private String action;

//    @JsonIgnore
//    private MenuDto menuParent;

    private Set<MenuDto> children;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

//    public MenuDto getMenuParent() {
//        return menuParent;
//    }
//
//    public void setMenuParent(MenuDto menuParent) {
//        this.menuParent = menuParent;
//    }

    public Set<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(Set<MenuDto> children) {
        this.children = children;
    }
}
