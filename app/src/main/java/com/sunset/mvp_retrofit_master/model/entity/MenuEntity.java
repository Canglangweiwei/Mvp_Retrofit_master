package com.sunset.mvp_retrofit_master.model.entity;

/**
 * 导航菜单列表
 */
public class MenuEntity {

    private int id;
    private String name;
    private String typeUrl;

    public MenuEntity() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeUrl() {
        return typeUrl;
    }

    public void setTypeUrl(String typeUrl) {
        this.typeUrl = typeUrl;
    }
}
