package com.sunset.mvp_retrofit_master.presenter;

import android.support.annotation.NonNull;

import com.sunset.mvp_retrofit_master.model.entity.GankEntity;
import com.sunset.mvp_retrofit_master.model.entity.GankListEntity;
import com.sunset.mvp_retrofit_master.model.network.Repository;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class GankPresenter implements GankContract.Presenter {

    private GankContract.View view;
    private Subscription mSubscription;

    /**
     * 因为Presenter是连接Model层和View层，所以在构造方法中从Activity/Fragment传递过来
     */
    public GankPresenter() {
        super();
    }

    /**
     * 这个方法可以调用Model中加载网络数据的方法
     */
    @Override
    public void loadMeiziList(int page) {
        // 调用Model中加载数据的方法
        mSubscription = Repository.getInstance().Get_Gank_Meizi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankListEntity>() {

                    @Override
                    public void call(GankListEntity gankListEntity) {
                        if (!gankListEntity.isError()) {
                            List<GankEntity> list = gankListEntity.getResults();
                            if (list == null || list.size() == 0) {
                                view.showNoMore();
                                return;
                            }
                            view.showMeizi(gankListEntity);
                        } else {
                            view.showLoadFailureMsg("数据加载失败");
                        }
                    }
                }, new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        view.showLoadFailureMsg("数据加载失败");
                    }
                });
    }

    @Override
    public void attachView(@NonNull GankContract.View view) {
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
