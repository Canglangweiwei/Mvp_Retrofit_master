package com.sunset.mvp_retrofit_master.presenter;

import android.support.annotation.NonNull;

import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;
import com.sunset.mvp_retrofit_master.model.network.Repository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * <p>
 * 新闻
 * </p>
 * Created by weiwei on 2016/12/2 14:53
 */
@SuppressWarnings("ALL")
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private Subscription mSubscription;

    /**
     * 因为Presenter是连接Model层和View层，所以在构造方法中从Activity/Fragment传递过来
     */
    public NewsPresenter() {
        super();
    }

    /**
     * 这个方法可以调用Model中加载网络数据的方法
     *
     * @param type 类型
     */
    @Override
    public void loadNewsList(String type) {
        view.showLoading();
        // 调用Model中加载数据的方法
        mSubscription = Repository.getInstance().Get_news(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsEntity>() {

                    @Override
                    public void call(NewsEntity newsEntity) {
                        view.dismissLoading();
                        if (0 == newsEntity.getError_code()) {
                            List<NewsEntity.Result.Data> list = newsEntity.getResult().getData();
                            if (list == null || list.size() == 0) {
                                view.showNoMore();
                                return;
                            }
                            view.showNews(newsEntity);
                        } else {
                            view.showLoadFailureMsg(newsEntity.getReason());
                        }
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        view.dismissLoading();
                        view.showLoadFailureMsg("数据加载失败");
                    }
                });
    }

    @Override
    public void attachView(@NonNull NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null
                && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
