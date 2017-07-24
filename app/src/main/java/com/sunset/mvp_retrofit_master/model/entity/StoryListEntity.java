package com.sunset.mvp_retrofit_master.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


@SuppressWarnings("ALL")
public class StoryListEntity implements Parcelable {

    private String date;
    private List<StoryEntity> stories;
    private List<TopStoryEntity> top_stories;

    public List<TopStoryEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoryEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoryEntity> stories) {
        this.stories = stories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }

    public StoryListEntity() {
        super();
    }

    protected StoryListEntity(Parcel in) {
        this.date = in.readString();
        this.stories = in.createTypedArrayList(StoryEntity.CREATOR);
        this.top_stories = in.createTypedArrayList(TopStoryEntity.CREATOR);
    }

    public static final Parcelable.Creator<StoryListEntity> CREATOR = new Parcelable.Creator<StoryListEntity>() {
        public StoryListEntity createFromParcel(Parcel source) {
            return new StoryListEntity(source);
        }

        public StoryListEntity[] newArray(int size) {
            return new StoryListEntity[size];
        }
    };
}