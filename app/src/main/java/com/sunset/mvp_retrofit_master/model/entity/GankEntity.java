package com.sunset.mvp_retrofit_master.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 妹子
 * </p>
 * Created by weiwei on 2016/7/25
 */
@SuppressWarnings("all")
public class GankEntity implements Serializable {

    private String url;
    private String type;
    private String desc;
    private String who;
    private boolean used;
    private Date createdAt;
    private Date updatedAt;
    private Date publishedAt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public GankEntity() {
        super();
    }
}
