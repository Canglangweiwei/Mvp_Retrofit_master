package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 视频列表
 */
@SuppressWarnings("ALL")
public class GankActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.right_button)
    TextView right_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        ButterKnife.bind(this);

        initToolbar();
        initView();
    }

    @Override
    protected void initView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        GankFragment fragment = GankFragment.newInstance("www", "eee");
        transaction.replace(R.id.ar_fl, fragment);
        transaction.commit();
    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
        toolbar.setNavigationIcon(com.boredream.bdvideoplayer.R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GankActivity.this, SlideGankMeiziActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
