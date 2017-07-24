package com.sunset.mvp_retrofit_master.model.network.api.story;

import com.sunset.mvp_retrofit_master.model.entity.StoryDetailEntity;
import com.sunset.mvp_retrofit_master.model.entity.StoryListEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　魏巍 版权所有(c)2016<br>
 * <b>作者</b>：　　  weiwei<br>
 * <b>创建日期</b>：　17-3-13<br>
 * <b>描述</b>：　　　故事<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
@SuppressWarnings("ALL")
public interface IStroryApiService {

    /**
     * 获取最新的故事
     *
     * @return
     */
    @GET("stories/latest")
    Observable<StoryListEntity> getLatestStories();

    /**
     * 按日期获取故事
     *
     * @param date
     * @return
     */
    @GET("stories/before/{date}")
    Observable<StoryListEntity> getBeforeStories(@Path("date") String date);

    /**
     * 获取故事详情
     *
     * @param id
     * @return
     */
    @GET("story/{id}")
    Observable<StoryDetailEntity> getStoriesDetail(@Path("id") int id);
}
