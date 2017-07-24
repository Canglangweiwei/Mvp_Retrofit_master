package com.boredream.bdvideoplayer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 视频数据类请实现本接口
 */
@SuppressWarnings("ALL")
public interface IVideoInfo extends Serializable {

    /**
     * 视频id
     */
    String getId();

    /**
     * 视频标题
     */
    String getName();

    /**
     * 视频播放路径 url / file path
     */
    String getPath();

    /**
     * 视频缩略图
     */
    String getThumb();

    /**
     * 视频描述
     */
    String getDesc();

    /**
     * 男主角
     */
    String getHero();

    /**
     * 女主角
     */
    String getHeroine();

    /**
     * 年代
     */
    String getYears();

    /**
     * 国家
     */
    String getCountry();

    /**
     * 类型
     */
    String getCategory();

    /**
     * 语言
     */
    String getLanguage();

    /**
     * 片长
     */
    String getShowTime();

    /**
     * 发布时间
     */
    String getPublishTime();

    /**
     * 上映时间
     */
    String getPlaytime();

    /**
     * 评分
     */
    String getImdb();

    /**
     * 导演
     */
    List<String> getDirectors();

    /**
     * 来源
     */
    String getSource();

    /**
     * 影片花絮
     */
    List<String> getPreviewImage();
}
