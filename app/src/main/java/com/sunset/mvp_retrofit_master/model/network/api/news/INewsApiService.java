package com.sunset.mvp_retrofit_master.model.network.api.news;

import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　魏巍 版权所有(c)2016<br>
 * <b>作者</b>：　　  weiwei<br>
 * <b>创建日期</b>：　17-3-13<br>
 * <b>描述</b>：　　　新闻<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
public interface INewsApiService {

    /**
     * 获取新闻列表
     *
     * @param type 新闻类型
     */
    @GET("index?key=9e05423f7ac6acf6d0dce3425c4ea9fe")
    Observable<NewsEntity> Get_news(@Query("type") String type);
}
