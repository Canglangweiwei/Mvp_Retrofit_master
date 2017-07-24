package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.GankEntity;
import com.sunset.mvp_retrofit_master.util.UIUtils;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>
 * 详情页面
 * </p>
 * Created by weiwei on 2017/6/23 0023.
 */
@SuppressWarnings("ALL")
public class GankDetailActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.meizi_poster)
    ImageView meiziPoster;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    // 片名
    @Bind(R.id.layout_name)
    LinearLayout layout_name;
    @Bind(R.id.name)
    TextView name;

    // 译名
    @Bind(R.id.layout_translationName)
    LinearLayout layout_translationName;
    @Bind(R.id.translationName)
    TextView translationName;

    // 年代
    @Bind(R.id.layout_years)
    LinearLayout layout_years;
    @Bind(R.id.years)
    TextView years;

    // 国家
    @Bind(R.id.layout_country)
    LinearLayout layout_country;
    @Bind(R.id.country)
    TextView country;

    // 类型
    @Bind(R.id.layout_category)
    LinearLayout layout_category;
    @Bind(R.id.category)
    TextView category;

    // 语言
    @Bind(R.id.layout_language)
    LinearLayout layout_language;
    @Bind(R.id.language)
    TextView language;

    // 字幕
    @Bind(R.id.layout_subtitle)
    LinearLayout layout_subtitle;
    @Bind(R.id.subtitle)
    TextView subtitle;

    // 字幕
    @Bind(R.id.layout_showTime)
    LinearLayout layout_showTime;
    @Bind(R.id.showTime)
    TextView showTime;

    // 集数
    @Bind(R.id.layout_episodeNumber)
    LinearLayout layout_episodeNumber;
    @Bind(R.id.episodeNumber)
    TextView episodeNumber;

    // 来源
    @Bind(R.id.layout_source)
    LinearLayout layout_source;
    @Bind(R.id.source)
    TextView source;

    // IMDB评分
    @Bind(R.id.layout_imdb)
    LinearLayout layout_imdb;
    @Bind(R.id.imdb)
    TextView imdb;

    // 发布时间
    @Bind(R.id.layout_publishTime)
    LinearLayout layout_publishTime;
    @Bind(R.id.publishTime)
    TextView publishTime;

    // 上映时间
    @Bind(R.id.layout_playtime)
    LinearLayout layout_playtime;
    @Bind(R.id.playtime)
    TextView playtime;

    // 视频格式
    @Bind(R.id.layout_fileFormat)
    LinearLayout layout_fileFormat;
    @Bind(R.id.fileFormat)
    TextView fileFormat;

    // 视频尺寸
    @Bind(R.id.layout_videoSize)
    LinearLayout layout_videoSize;
    @Bind(R.id.videoSize)
    TextView videoSize;

    // 文件大小
    @Bind(R.id.layout_fileSize)
    LinearLayout layout_fileSize;
    @Bind(R.id.fileSize)
    TextView fileSize;

    // 导演
    @Bind(R.id.layout_director)
    LinearLayout layout_director;
    @Bind(R.id.director)
    TextView director;

    // 编辑
    @Bind(R.id.layout_screenWriters)
    LinearLayout layout_screenWriters;
    @Bind(R.id.screenWriters)
    TextView screenWriters;

    // 主演
    @Bind(R.id.layout_leadingPlayers)
    LinearLayout layout_leadingPlayers;
    @Bind(R.id.leadingPlayers)
    TextView leadingPlayers;

    // 描述
    @Bind(R.id.layout_description)
    LinearLayout layout_description;
    @Bind(R.id.description)
    TextView description;

    // 预览
    @Bind(R.id.layout_previewImage)
    LinearLayout layout_previewImage;
    @Bind(R.id.previewImage)
    ImageView previewImage;

    private GankEntity meizi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);
        initToolbar();
        parseIntent();
        initView();
    }

    @Override
    protected void initView() {
        collapsingToolbar.setTitle(meizi.getWho());

        setText(name, layout_name, meizi.getWho());                  // 1. 名字
        setText(translationName, layout_translationName, "");     // 2. 译名
        setText(years, layout_years, "");                // 3. 年代
        setText(country, layout_country, "");            // 4. 国家
        setText(category, layout_category, "");          // 5．类型
        setText(language, layout_language, "");          // 6. 语言
        setText(showTime, layout_showTime, "");          // 7. 片长
        setText(publishTime, layout_publishTime, new SimpleDateFormat("yyyy/MM/dd hh:MM:ss").format(meizi.getCreatedAt()));    // 8. 发布时间
        setText(playtime, layout_playtime, new SimpleDateFormat("yyyy/MM/dd hh:MM:ss").format(meizi.getPublishedAt()));          // 9. 上映时间
        setText(subtitle, layout_subtitle, "");          // 10. 字母
        setText(fileFormat, layout_fileFormat, "");      // 11. 文件格式
        setText(videoSize, layout_videoSize, "");        // 12.　视频尺寸
        setText(fileSize, layout_fileSize, "");        // 12.　文件大小
        setText(imdb, layout_imdb, "");                  // 13. 评分
        setText(episodeNumber, layout_episodeNumber, "");// 14 集数
        setText(source, layout_source, "");               // 15. 来源
        setText(director, layout_director, "");// 16. 导演
        setText(screenWriters, layout_screenWriters, "");// 17. 编辑
        setText(leadingPlayers, layout_leadingPlayers, "");// 18. 主演
        setText(description, layout_description, meizi.getDesc());// 19. 描述

        Glide.with(this)
                .load(meizi.getUrl())
                .into(meiziPoster);

        Glide.with(this)
                .load(meizi.getUrl())
                .asBitmap()
                .into(new BitmapImageViewTarget(previewImage) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        float ratio = width * 1.0F / height;
                        float targetHeight = UIUtils.getScreenWidth() * 1.0F / ratio;

                        ViewGroup.LayoutParams params = previewImage.getLayoutParams();
                        params.height = (int) targetHeight;
                        previewImage.setLayoutParams(params);
                        previewImage.setImageBitmap(resource);
                    }
                });
    }

    @Override
    protected void createPresenter() {

    }

    public void setText(TextView textView, LinearLayout layout, String str) {
        textView.setText(TextUtils.isEmpty(str) ? "" : str);
        layout.setVisibility(TextUtils.isEmpty(str) ? View.GONE : View.VISIBLE);
    }

    /**
     * 图片点击事件
     */
    @OnClick({R.id.meizi_poster, R.id.previewImage})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.meizi_poster:
                break;
            case R.id.previewImage:
                Intent intent = new Intent(GankDetailActivity.this, PictureActivity.class);
                intent.putExtra(PictureActivity.IMG_URL, meizi.getUrl());
                intent.putExtra(PictureActivity.IMG_DESC, meizi.getDesc());
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(GankDetailActivity.this,
                                view, PictureActivity.TRANSIT_PIC);
                // Android 5.0+
                try {
                    ActivityCompat.startActivity(GankDetailActivity.this, intent, optionsCompat.toBundle());
                } catch (Exception e) {
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 解析页面传值
     */
    private void parseIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            meizi = new GankEntity();
        } else {
            meizi = (GankEntity) bundle.getSerializable("gank_detail_info");
        }
    }

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
