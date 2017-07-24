package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

/**
 * <p>
 * 欢迎页面
 * </p>
 * Created by weiwei on 2017/6/29 0029.
 */
public class WelcomeActivity extends BaseActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        initView();
    }

    @Override
    protected void initView() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, NewsActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void onDestroy() {
        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
            mRunnable = null;
        }
        super.onDestroy();
    }
}
