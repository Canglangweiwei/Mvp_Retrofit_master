package com.sunset.mvp_retrofit_master.presenter;


import android.support.annotation.NonNull;

import com.sunset.mvp_retrofit_master.model.entity.StoryDetailEntity;
import com.sunset.mvp_retrofit_master.model.network.Repository;
import com.sunset.mvp_retrofit_master.util.HtmlUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class StoryDetailPresenter implements StoryDetailContract.Presenter {

    private int id;
    private StoryDetailContract.View view;
    private Subscription mSubscription;

    public StoryDetailPresenter(int id) {
        super();
        this.id = id;
    }

    @Override
    public void getNewsDetailData() {
        mSubscription = Repository.getInstance().getStoriesDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<StoryDetailEntity>() {

                    @Override
                    public void call(StoryDetailEntity storyDetailEntity) {
                        StringBuffer stringBuffer = HtmlUtil.handleHtml(storyDetailEntity.getBody());
                        view.showData(storyDetailEntity.getImage(), storyDetailEntity.getTitle(), storyDetailEntity.getImage_source(), stringBuffer);
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        view.showLoadFailureMsg("数据加载失败");
                    }
                });
    }

    @Override
    public void attachView(@NonNull StoryDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
