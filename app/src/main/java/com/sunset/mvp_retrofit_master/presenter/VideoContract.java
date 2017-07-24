package com.sunset.mvp_retrofit_master.presenter;

import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;
import com.sunset.mvp_retrofit_master.presenter.base.BasePresenter;
import com.sunset.mvp_retrofit_master.presenter.base.BaseView;

import java.util.List;

public interface VideoContract {

    interface Presenter extends BasePresenter<View> {
        /**
         * 传递参数到Model去加载网络数据
         */
        void loadVideoList();
    }

    interface View extends BaseView {

        /**
         * 获取得到网络数据显示在界面上
         */
        void showVideo(List<VideoEntity.Data.DataBean> listData);

        void showNoMore();
    }
}
