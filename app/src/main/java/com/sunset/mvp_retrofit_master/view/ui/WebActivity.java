package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sunset.mvp_retrofit_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新闻详情页面
 */
@SuppressWarnings("ALL")
public class WebActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webView)
    WebView webView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        initToolbar();
        initView();
    }

    private void initView() {
        url = getIntent().getStringExtra("url");
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl(url);
        webView.setOnKeyListener(new View.OnKeyListener() { // webview can
            // go back
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
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

    @OnClick({R.id.btn_share})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                share();
                break;
            default:
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
        intent.putExtra(Intent.EXTRA_TEXT, "来自「纯净日报」的分享：" + url);
        startActivity(Intent.createChooser(intent, "「纯净日报」的分享"));
    }

    /**
     * 清空缓存
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
        webView.removeAllViews();
        webView.destroy();
    }
}
