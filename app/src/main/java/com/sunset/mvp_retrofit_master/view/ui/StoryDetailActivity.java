package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.presenter.StoryDetailContract;
import com.sunset.mvp_retrofit_master.presenter.StoryDetailPresenter;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * <p>
 * 故事详情页面
 * </p>
 * Created by weiwei on 2017/6/29 0029.
 */
@SuppressWarnings("ALL")
public class StoryDetailActivity extends BaseActivity implements StoryDetailContract.View {

	@Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.iv_header)
    ImageView mIvHeader;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_source)
    TextView mTvSource;
    
    @Bind(R.id.wv_news)
    WebView mWvNews;

    private String title;
    private int id;

    private StoryDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);

        initToolbar();
        parseIntent();
        createPresenter();
    }

    /**
     * 解析页面传值
     */
    private void parseIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void createPresenter() {
        presenter = new StoryDetailPresenter(id);
        presenter.attachView(this);
        presenter.getNewsDetailData();
    }

    @OnClick({R.id.iv_share})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                share();
                break;
        }
    }

    /**
     * 分享
     */
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「纯净日报」的分享：" + title + "，http://daily.zhihu.com/story/" + getIntent().getIntExtra("new", 0));
        startActivity(Intent.createChooser(intent, title));
    }

    /**
     * 初始化页面内容
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
        toolbar.setNavigationIcon(com.boredream.bdvideoplayer.R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        collapsingToolbar.setTitle("福利社");
    }

    @Override
    public void showData(String image, String title, String image_source, StringBuffer body) {
        Glide.with(this).load(image).into(mIvHeader);
        this.title = title;
        mTvTitle.setText(title);
        mTvSource.setText(image_source);
        mWvNews.setDrawingCacheEnabled(true);
        mWvNews.loadDataWithBaseURL("file:///android_asset/", body.toString(), "text/html", "utf-8", null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
