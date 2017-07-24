package com.sunset.mvp_retrofit_master.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.util.FileUtil;
import com.sunset.mvp_retrofit_master.util.NetUtil;
import com.sunset.mvp_retrofit_master.model.network.httputil.OkHttpImageDownloader;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片查看
 */
@SuppressWarnings("ALL")
public class PictureActivity extends BaseActivity {

    public static final String IMG_URL = "img_url";
    public static final String IMG_DESC = "img_desc";
    public static final String TRANSIT_PIC = "picture";

    private String img_url;
    private String img_desc;

    @Bind(R.id.layout_pic)
    RelativeLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.iv_meizhi_pic)
    ImageView iv_meizhi_pic;

    @OnClick(R.id.save_img)
    void saveImg() {
        /**
         * 保存图片
         */
        saveImage();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);

        parseIntent();
        initView();
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
        txtTitle.setText("图片展示");
    }

    @Override
    protected void initView() {
        initToolbar();
        // 设置共享元素
        ViewCompat.setTransitionName(iv_meizhi_pic, TRANSIT_PIC);
        Glide.with(this).load(img_url).centerCrop().into(iv_meizhi_pic);
        new PhotoViewAttacher(iv_meizhi_pic);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.clear(iv_meizhi_pic);
        ButterKnife.unbind(this);
    }

    private void parseIntent() {
        img_url = getIntent().getStringExtra(IMG_URL);
        img_desc = getIntent().getStringExtra(IMG_DESC);
    }

    /**
     * 保存图片
     */
    private void saveImage() {
        // WiFi条件下保存图片
        if (NetUtil.getNetworkType() == NetUtil.NETWORK_TYPE_WIFI) {
            if (!TextUtils.isEmpty(img_url)) {
                try {
                    OkHttpImageDownloader.download(img_url);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Toast.makeText(this, "图片保存至目录【" + FileUtil.ADPATH + "】下", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Snackbar snackbar = Snackbar.make(drawerLayout, "不是WIFI环境,就不去下载图片了", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorDeepred));
            snackbar.show();
        }
    }
}
