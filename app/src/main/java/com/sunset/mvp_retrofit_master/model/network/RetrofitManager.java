package com.sunset.mvp_retrofit_master.model.network;

import android.content.Context;

import com.sunset.mvp_retrofit_master.BuildConfig;
import com.sunset.mvp_retrofit_master.config.AppConfig;
import com.sunset.mvp_retrofit_master.config.MVPApplication;
import com.sunset.mvp_retrofit_master.model.network.api.gank.IGankApiService;
import com.sunset.mvp_retrofit_master.model.network.api.news.INewsApiService;
import com.sunset.mvp_retrofit_master.model.network.api.story.IStroryApiService;
import com.sunset.mvp_retrofit_master.model.network.api.video.IVideoApiService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * 初始化网络请求工具
 * </p>
 * Created by weiwei on 2017/6/23.
 */
@SuppressWarnings("ALL")
public class RetrofitManager {

    private static INewsApiService iNewsApiService;
    private static IGankApiService iGankApiService;
    private static IVideoApiService iVideoApiService;
    private static IStroryApiService iStroryApiService;

    private static RetrofitManager retrofitManager;

    private RetrofitManager(Context context) {
        int cacheSize = 10 * 1024 * 1024;

        String cachePath;
        if (null == context) {
            cachePath = "cache.flll";
        } else {
            cachePath = context.getCacheDir().getAbsolutePath() + File.separator + "cache.flll";
        }
        Cache cache = new Cache(new File(cachePath), cacheSize);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        /**
         * 新闻
         */
        Retrofit newsRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.uri_news)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        /**
         * 妹子
         */
        Retrofit meiZiRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.uri_meizi)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        /**
         * 视频
         */
        Retrofit videoRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.uri_video)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        /**
         * 故事
         */
        Retrofit storyRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.uri_story)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        iNewsApiService = newsRetrofit.create(INewsApiService.class);
        iGankApiService = meiZiRetrofit.create(IGankApiService.class);
        iVideoApiService = videoRetrofit.create(IVideoApiService.class);
        iStroryApiService = storyRetrofit.create(IStroryApiService.class);
    }

    /**
     * 单例模式
     */
    public static synchronized RetrofitManager getInstance() {
        RetrofitManager tmp = retrofitManager;
        if (tmp == null) {
            synchronized (RetrofitManager.class) {
                tmp = retrofitManager;
                if (tmp == null) {
                    if (MVPApplication.sApp != null) {
                        tmp = new RetrofitManager(MVPApplication.sApp);
                    } else {
                        tmp = new RetrofitManager(null);
                    }
                    retrofitManager = tmp;
                }
            }
        }
        return tmp;
    }

    /**
     * 新闻
     */
    public INewsApiService getNewsApiService() {
        return iNewsApiService;
    }

    /**
     * 干货
     */
    public IGankApiService getGankApiService() {
        return iGankApiService;
    }

    /**
     * 视频
     */
    public IVideoApiService getVideoApiService() {
        return iVideoApiService;
    }

    /**
     * 故事
     */
    public IStroryApiService getStroryApiService() {
        return iStroryApiService;
    }
}
