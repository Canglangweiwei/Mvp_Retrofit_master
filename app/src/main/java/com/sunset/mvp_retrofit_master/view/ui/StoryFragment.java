package com.sunset.mvp_retrofit_master.view.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sunset.mvp_retrofit_master.model.entity.StoryEntity;
import com.sunset.mvp_retrofit_master.presenter.StoryListContract;
import com.sunset.mvp_retrofit_master.presenter.StoryListPresenter;
import com.sunset.mvp_retrofit_master.util.LogUtil;
import com.sunset.mvp_retrofit_master.view.adapter.StoryListAdapter;
import com.sunset.mvp_retrofit_master.view.base.GridPagingFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 妹子列表
 * </p>
 * Created by weiwei on 2017/6/22 .
 */
@SuppressWarnings("ALL")
public class StoryFragment extends GridPagingFragment
        implements StoryListContract.View {

    private List<StoryEntity> listData = new ArrayList<>();

    private StoryListContract.Presenter presenter;

    // 当前日期
    private String currentDate;

    public static StoryFragment newInstance(String www, String eee) {
        StoryFragment fragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("www", www);
        bundle.putString("eee", eee);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private void createPresenter() {
        presenter = new StoryListPresenter();
        presenter.attachView(this);
    }

    @Override
    public void loadData(int pageIndex) {
        if (pageIndex == 1) {
            presenter.getNewsListData();
            LogUtil.logd("getNewsListData");
        } else {
            presenter.getBeforeNewsListData(currentDate);
            LogUtil.logd("getBeforeNewsListData：date=" + currentDate);
        }
    }

    @Override
    public List getDataList() {
        return listData;
    }

    @Override
    public RecyclerView.Adapter getAdapter(List list) {
        return new StoryListAdapter(list);
    }

    @Override
    public int defaultPartOnlineNumber() {
        return 2;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {
        // 请求数据失败返回失败信息
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        onLoaded(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void getDate(String date) {
        currentDate = date;
    }

    @Override
    public void load(ArrayList<StoryEntity> list) {
        listData.clear();
        listData.addAll(list);
        onLoaded(true);
    }

    @Override
    public void refresh(ArrayList<StoryEntity> list) {
        listData.clear();
        listData.addAll(list);
        onLoaded(true);
    }
}
