package com.sunset.mvp_retrofit_master.model.network.api.gank;

import com.sunset.mvp_retrofit_master.model.entity.GankListEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
public interface IGankApiService {

    /**
     * 获取图片列表
     */
    @GET("data/福利/30/{page}")
    Observable<GankListEntity> Get_Gank_Meizi(@Path("page") int page);
}
