package com.sunset.mvp_retrofit_master.presenter;

import com.sunset.mvp_retrofit_master.presenter.base.BasePresenter;
import com.sunset.mvp_retrofit_master.presenter.base.BaseView;

/**
 * <p>
 * 故事详情
 * </p>
 * Created by weiwei on 2017/6/29 0029.
 */
@SuppressWarnings("ALL")
public interface StoryDetailContract {

    interface View extends BaseView {
        void showData(String image, String title, String image_source, StringBuffer body);
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsDetailData();
    }
}
