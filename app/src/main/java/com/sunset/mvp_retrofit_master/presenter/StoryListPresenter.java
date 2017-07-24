package com.sunset.mvp_retrofit_master.presenter;

import android.support.annotation.NonNull;

import com.sunset.mvp_retrofit_master.model.entity.StoryEntity;
import com.sunset.mvp_retrofit_master.model.entity.StoryListEntity;
import com.sunset.mvp_retrofit_master.model.network.Repository;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class StoryListPresenter implements StoryListContract.Presenter {

    private StoryListContract.View view;
    private Subscription mSubscription;
    private ArrayList<StoryEntity> listdata = new ArrayList<>();

    public StoryListPresenter() {
        super();
    }

    /**
     * 获取当前日期之前的数据
     * @param date
     */
    @Override
    public void getBeforeNewsListData(String date) {
        mSubscription = Repository.getInstance().getBeforeStories(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<StoryListEntity>() {
                    @Override
                    public void call(StoryListEntity storyListEntity) {
                        view.getDate(storyListEntity.getDate());
                        for (StoryEntity news : storyListEntity.getStories()) {
                            listdata.add(news);
                        }
                        view.load(listdata);
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    /**
     * 获取最新的消息
     */
    @Override
    public void getNewsListData() {
        mSubscription = Repository.getInstance().getLatestStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<StoryListEntity>() {
                    @Override
                    public void call(StoryListEntity storyListEntity) {
                        view.getDate(storyListEntity.getDate());
                        listdata.clear();
                        for (StoryEntity news : storyListEntity.getStories()) {
                            listdata.add(news);
                        }
                        view.refresh(listdata);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    /**
     * 湖区最新的消息
     */
    @Override
    public void getRefreshNewsListData() {
        mSubscription = Repository.getInstance().getLatestStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<StoryListEntity>() {
                    @Override
                    public void call(StoryListEntity storyListEntity) {
                        view.getDate(storyListEntity.getDate());
                        listdata.clear();
                        for (StoryEntity news : storyListEntity.getStories()) {
                            listdata.add(news);
                        }
                        view.refresh(listdata);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void attachView(@NonNull StoryListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
