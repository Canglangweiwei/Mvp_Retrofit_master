package com.sunset.mvp_retrofit_master.view.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sunset.mvp_retrofit_master.model.entity.GankEntity;
import com.sunset.mvp_retrofit_master.model.entity.GankListEntity;
import com.sunset.mvp_retrofit_master.presenter.GankContract;
import com.sunset.mvp_retrofit_master.presenter.GankPresenter;
import com.sunset.mvp_retrofit_master.view.adapter.GankListAdapter;
import com.sunset.mvp_retrofit_master.view.base.GridPagingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 妹子列表
 * </p>
 * Created by weiwei on 2017/6/22 .
 */
@SuppressWarnings("ALL")
public class GankFragment extends GridPagingFragment
        implements GankContract.View {

    private List<GankEntity> gankEntityList = new ArrayList<>();

    private GankContract.Presenter mPresenter;

    public static GankFragment newInstance(String www, String eee) {
        GankFragment fragment = new GankFragment();
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
        mPresenter = new GankPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void loadData(int pageIndex) {
        mPresenter.loadMeiziList(pageIndex);
    }

    @Override
    public List getDataList() {
        return gankEntityList;
    }

    @Override
    public RecyclerView.Adapter getAdapter(List list) {
        return new GankListAdapter(list);
    }

    @Override
    public int defaultPartOnlineNumber() {
        return 2;
    }

    @Override
    public void showMeizi(GankListEntity gankListEntity) {
        if (gankListEntity == null
                || gankListEntity.getResults() == null
                || gankListEntity.getResults().size() == 0) {
            return;
        }
        gankEntityList = gankListEntity.getResults();
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
        mPresenter.detachView();
    }
}
