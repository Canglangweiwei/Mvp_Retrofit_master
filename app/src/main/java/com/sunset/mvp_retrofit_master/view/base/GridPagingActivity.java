package com.sunset.mvp_retrofit_master.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 分页Activity
 * Created by weiwei on 2016/6/21.
 */
@SuppressWarnings("ALL")
public abstract class GridPagingActivity extends SingleFragmentActivity implements GridPageable {

    private GridPagingFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /**
         * 使用activity的方法替换fragment中的方法
         */
        fragment = new GridPagingFragment() {

            @Override
            public void loadData(int pageIndex) {
                GridPagingActivity.this.loadData(pageIndex);
            }

            @Override
            public List getDataList() {
                return GridPagingActivity.this.getDataList();
            }

            @Override
            public RecyclerView.Adapter getAdapter(List list) {
                return GridPagingActivity.this.getAdapter(list);
            }

            @Override
            public int getIndexStart() {
                return GridPagingActivity.this.getIndexStart();
            }

            @Override
            public RecyclerView.LayoutManager getLayoutManager() {
                return GridPagingActivity.this.getLayoutManager();
            }

            @Override
            public boolean getHasFixedSize() {
                return GridPagingActivity.this.getHasFixedSize();
            }

            @Override
            public GridLayoutManager getGridLayoutManager() {
                return GridPagingActivity.this.getGridLayoutManager();
            }

            @Override
            public int defaultPartOnlineNumber() {
                return GridPagingActivity.this.defaultPartOnlineNumber();
            }
        };
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return fragment;
    }

    @Override
    public void onLoaded(boolean isSuccess) {
        fragment.onLoaded(isSuccess);
    }

    @Override
    public void refreshData() {
        fragment.refreshData();
    }

    @Override
    public int getIndexStart() {
        return 1;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public boolean getHasFixedSize() {
        return true;
    }

    @Override
    public GridLayoutManager getGridLayoutManager() {
        return new GridLayoutManager(this, defaultPartOnlineNumber());
    }

    @Override
    public int defaultPartOnlineNumber() {
        return 2;
    }
}
