package com.sunset.mvp_retrofit_master.model.entity;

import com.boredream.bdvideoplayer.bean.IVideoInfo;

import java.util.List;

@SuppressWarnings("ALL")
public class VideoDetailEntity implements IVideoInfo {

    public String id;                   // ID
    public String name;                 // 标题
    public String path;                 // 視頻路径
    public String thumb;                // 視頻縮略圖
    public String desc;                 // 視頻描述
    public String hero;                 // 男主角
    public String heroine;              // 女主角
    public String years;                // 年代
    public String country;              // 国家
    public String category;             // 类型
    public String language;             // 语言
    public String showTime;             // 片长
    public String publishTime;          // 发布时间
    public String playtime;             // 上映时间
    public String imdb;                 // 评分
    public String source;               // 来源
    public List<String> directors;      // 导演
    public List<String> previewImage;   // 影片花絮

    public VideoDetailEntity() {
        super();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getThumb() {
        return thumb;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getHero() {
        return hero;
    }

    @Override
    public String getHeroine() {
        return heroine;
    }

    @Override
    public String getYears() {
        return years;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String getShowTime() {
        return showTime;
    }

    @Override
    public String getPublishTime() {
        return publishTime;
    }

    @Override
    public String getPlaytime() {
        return playtime;
    }

    @Override
    public String getImdb() {
        return imdb;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public List<String> getDirectors() {
        return directors;
    }

    @Override
    public List<String> getPreviewImage() {
        return previewImage;
    }
}
