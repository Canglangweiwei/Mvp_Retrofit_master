package com.sunset.mvp_retrofit_master.model.network;

import com.sunset.mvp_retrofit_master.model.entity.GankListEntity;
import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;
import com.sunset.mvp_retrofit_master.model.entity.StoryDetailEntity;
import com.sunset.mvp_retrofit_master.model.entity.StoryListEntity;
import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;
import com.sunset.mvp_retrofit_master.model.network.api.gank.IGankApiService;
import com.sunset.mvp_retrofit_master.model.network.api.news.INewsApiService;
import com.sunset.mvp_retrofit_master.model.network.api.story.IStroryApiService;
import com.sunset.mvp_retrofit_master.model.network.api.video.IVideoApiService;

import rx.Observable;

/**
 * <p>
 * 管理仓库
 * </p>
 * Created by weiwei on 2017/6/26 0026.
 */
@SuppressWarnings("ALL")
public class Repository {

    private static volatile INewsApiService iNewsApiService;
    private static volatile IGankApiService iGankApiService;
    private static volatile IVideoApiService iVideoApiService;
    private static volatile IStroryApiService iStroryApiService;

    private static volatile Repository repository;

    private Repository() {
        super();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static Repository getInstance() {
        Repository tmp = repository;
        if (tmp == null) {
            synchronized (Repository.class) {
                tmp = repository;
                if (tmp == null) {
                    tmp = new Repository();
                    repository = tmp;
                    iNewsApiService = RetrofitManager.getInstance().getNewsApiService();
                    iGankApiService = RetrofitManager.getInstance().getGankApiService();
                    iVideoApiService = RetrofitManager.getInstance().getVideoApiService();
                    iStroryApiService = RetrofitManager.getInstance().getStroryApiService();
                }
            }
        }
        return tmp;
    }

    /**
     * 获取干货列表
     */
    public Observable<GankListEntity> Get_Gank_Meizi(int page) {
        return iGankApiService.Get_Gank_Meizi(page);
    }

    /**
     * 获取新闻列表
     */
    public Observable<NewsEntity> Get_news(String type) {
        return iNewsApiService.Get_news(type);
    }

    /**
     * 获取视频列表
     */
    public Observable<VideoEntity> Get_Video() {
        return iVideoApiService.Get_Video();
    }

    /**
     * 获取最新的故事
     */
    public Observable<StoryListEntity> getLatestStories() {
        return iStroryApiService.getLatestStories();
    }

    /**
     * 按日期获取故事
     */
    public Observable<StoryListEntity> getBeforeStories(String date) {
        return iStroryApiService.getBeforeStories(date);
    }

    /**
     * 获取故事详情
     */
    public Observable<StoryDetailEntity> getStoriesDetail(int id) {
        return iStroryApiService.getStoriesDetail(id);
    }
}
