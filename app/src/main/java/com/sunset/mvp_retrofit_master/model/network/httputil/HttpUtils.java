package com.sunset.mvp_retrofit_master.model.network.httputil;

import com.sunset.mvp_retrofit_master.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ==========================================================<br>
 * <b>版权</b>：　　　魏巍 版权所有(c)2016<br>
 * <b>作者</b>：　　  weiwei<br>
 * <b>创建日期</b>：　17-3-13<br>
 * <b>描述</b>：　　　网络请求工具<br>
 * <b>版本</b>：　    V1.0<br>
 * <b>修订历史</b>：　<br>
 * ==========================================================<br>
 */
@SuppressWarnings("ALL")
public class HttpUtils {

    private HttpUtils() {
        super();
    }

    public final static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(createHttpLoggingInterceptor())
            .build();

    private final static HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
