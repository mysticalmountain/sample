package com.sample.permission.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-8-25.
 */
@Entity
@Table
public class Menu extends com.sample.core.model.Entity {

    @Id
    @GenericGenerator(name = "menuId", strategy = "enhanced-table",
            parameters = {
                    @Parameter(name = "table_name", value = "seq_id"),
                    @Parameter(name = "value_column_name", value = "next"),
                    @Parameter(name = "segment_column_name", value = "segment_name"),
                    @Parameter(name = "segment_value", value = "seq_menu"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "menuId")
    private Long id;

    private String name;

    private String url;

    private Integer isShow;

    private Integer isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Permission_Menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    @OneToOne(targetEntity = Menu.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Menu parent;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Menu> sons;

//    @OneToMany(targetEntity = MenuPermission.class)
//    @JoinColumn(name = "id", referencedColumnName = "menu_id", nullable = false)
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "Menu_Permission",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> menuPermissions;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Set<Menu> getSons() {
        return sons;
    }

    public void setSons(Set<Menu> sons) {
        this.sons = sons;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Set<Permission> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }
}
