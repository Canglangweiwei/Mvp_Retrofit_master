package com.sunset.mvp_retrofit_master.view.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.boredream.bdvideoplayer.BDVideoView;
import com.boredream.bdvideoplayer.listener.SimpleOnVideoControlListener;
import com.boredream.bdvideoplayer.utils.DisplayUtils;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.VideoDetailEntity;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <p>
 * 视频播放页面
 * </p>
 * Created by weiwei on 2017/6/22 0022.
 */

public class VideoDetailActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vv)
    BDVideoView videoView;

    private VideoDetailEntity entity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        parseIntent();
        initToolbar();
        initVideo();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 解析页面传值
     */
    private void parseIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            entity = new VideoDetailEntity();
        } else {
            entity = (VideoDetailEntity) bundle.getSerializable("info");
        }
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
    }

    /**
     * 初始化播放器
     */
    private void initVideo() {
        videoView.setOnVideoControlListener(new SimpleOnVideoControlListener() {

            @Override
            public void onRetry(int errorStatus) {
                // TODO: 2017/6/20 调用业务接口重新获取数据
                // get info and call method "videoView.startPlayVideo(info);"
            }

            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(VideoDetailActivity.this);
            }
        });
        videoView.startPlayVideo(entity);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {// 竖屏显示
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            toolbar.setVisibility(View.VISIBLE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {// 横屏显示
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            toolbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if (!videoView.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }
}
