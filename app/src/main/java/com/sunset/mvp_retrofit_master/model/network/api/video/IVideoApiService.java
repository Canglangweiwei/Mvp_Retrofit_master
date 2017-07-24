package com.sunset.mvp_retrofit_master.model.network.api.video;

import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　魏巍 版权所有(c)2016<br>
 * <b>作者</b>：　　  weiwei<br>
 * <b>创建日期</b>：　17-3-13<br>
 * <b>描述</b>：　　　美女图片<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
public interface IVideoApiService {

    /**
     * 获取图片列表
     */
    @GET("neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-104&message_cursor=-1")
    Observable<VideoEntity> Get_Video();
}
