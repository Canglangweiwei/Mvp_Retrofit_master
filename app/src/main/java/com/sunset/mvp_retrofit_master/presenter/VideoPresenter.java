package com.sunset.mvp_retrofit_master.presenter;

import android.support.annotation.NonNull;

import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;
import com.sunset.mvp_retrofit_master.model.network.Repository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class VideoPresenter implements VideoContract.Presenter {

    private VideoContract.View view;
    private Subscription mSubscription;

    public VideoPresenter() {
        super();
    }

    @Override
    public void loadVideoList() {
        // 调用Model中加载数据的方法
        mSubscription = Repository.getInstance().Get_Video()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoEntity>() {

                    @Override
                    public void call(VideoEntity videoEntity) {
                        List<VideoEntity.Data.DataBean> listData = videoEntity.getData().getData();
                        if (listData == null || listData.size() == 0) {
                            view.showNoMore();
                            return;
                        }
                        for (VideoEntity.Data.DataBean bean : listData) {
                            if (bean.getType() != 1) {// 过滤掉不是视频的数据
                                listData.remove(bean);
                            }
                        }
                        view.showVideo(listData);
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        view.showLoadFailureMsg("数据加载失败");
                    }
                });
    }

    @Override
    public void attachView(@NonNull VideoContract.View view) {
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
