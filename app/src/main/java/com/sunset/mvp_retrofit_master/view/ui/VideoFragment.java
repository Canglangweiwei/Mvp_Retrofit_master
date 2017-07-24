package com.sunset.mvp_retrofit_master.view.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;
import com.sunset.mvp_retrofit_master.presenter.VideoContract;
import com.sunset.mvp_retrofit_master.presenter.VideoPresenter;
import com.sunset.mvp_retrofit_master.view.adapter.VideoAdapter;
import com.sunset.mvp_retrofit_master.view.base.ListPagingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 电影列表
 * </p>
 * Created by weiwei on 2017/6/22 .
 */
@SuppressWarnings("ALL")
public class VideoFragment extends ListPagingFragment implements VideoContract.View {

    private VideoPresenter presenter;
    private List<VideoEntity.Data.DataBean> mList;

    public static VideoFragment newInstance(String www, String eee) {
        VideoFragment fragment = new VideoFragment();
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
    }

    private void createPresenter() {
        presenter = new VideoPresenter();
        presenter.attachView(this);
    }

    @Override
    public void loadData(int pageIndex) {
        presenter.loadVideoList();
    }

    @Override
    public List getDataList() {
        return mList;
    }

    @Override
    public RecyclerView.Adapter getAdapter(List list) {
        return new VideoAdapter(list);
    }

    @Override
    public void showVideo(List<VideoEntity.Data.DataBean> listData) {
        if (listData != null){
            mList = listData;
        } else {
            mList = new ArrayList<>();
        }
        onLoaded(true);
    }

    @Override
    public void showNoMore() {
        Toast.makeText(getActivity(), "-------没有需要加载的数据-------", Toast.LENGTH_SHORT).show();
        onLoaded(false);
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
}
