package com.sunset.mvp_retrofit_master.model.event;


@SuppressWarnings("ALL")
public class Event {

    private int id;
    private String name;
    private String imageUrl;

    public Event() {
        super();
    }

    public Event(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Event(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
