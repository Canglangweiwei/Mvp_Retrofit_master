package com.sunset.mvp_retrofit_master.presenter;

import com.sunset.mvp_retrofit_master.model.entity.StoryEntity;
import com.sunset.mvp_retrofit_master.presenter.base.BasePresenter;
import com.sunset.mvp_retrofit_master.presenter.base.BaseView;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public interface StoryListContract {

    interface View extends BaseView {
        void getDate(String date);

        void load(ArrayList<StoryEntity> list);

        void refresh(ArrayList<StoryEntity> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getBeforeNewsListData(String date);

        void getNewsListData();

        void getRefreshNewsListData();
    }
}