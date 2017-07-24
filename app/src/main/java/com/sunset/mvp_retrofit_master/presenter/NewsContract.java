package com.sunset.mvp_retrofit_master.presenter;

import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;
import com.sunset.mvp_retrofit_master.presenter.base.BasePresenter;
import com.sunset.mvp_retrofit_master.presenter.base.BaseView;

public interface NewsContract {

    interface Presenter extends BasePresenter<View> {
        /**
         * 传递参数到Model去加载网络数据
         *
         * @param type 类型
         */
        void loadNewsList(String type);
    }

    interface View extends BaseView {

        /**
         * 获取得到网络数据显示在界面上
         */
        void showNews(NewsEntity newsEntity);

        void showNoMore();
    }
}
