package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.GankEntity;
import com.sunset.mvp_retrofit_master.model.entity.GankListEntity;
import com.sunset.mvp_retrofit_master.presenter.GankContract;
import com.sunset.mvp_retrofit_master.presenter.GankPresenter;
import com.sunset.mvp_retrofit_master.view.base.BaseActivity;
import com.sunset.mvp_retrofit_master.view.slidecard.CardDataItem;
import com.sunset.mvp_retrofit_master.view.slidecard.CardSlidePanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 滑动卡片 - 画廊效果展示
 */
public class SlideGankMeiziActivity extends BaseActivity implements GankContract.View {

    private GankContract.Presenter presenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.txtTitle)
    TextView txtTitle;

    @Bind(R.id.image_slide_panel)
    CardSlidePanel mCardSlidePanel;

    // 滑动的卡片item集合
    private List<CardDataItem> dataList = new ArrayList<>();

    // Gank妹子集合
    private List<GankEntity> meizis = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_card);
        ButterKnife.bind(this);
        initToolBar();
        createPresenter();
        initView();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
        mToolbar.setNavigationIcon(com.boredream.bdvideoplayer.R.drawable.ic_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtTitle.setText("滑动卡片 - 画廊效果展示");
    }

    @Override
    protected void initView() {
        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {

            }

            @Override
            public void onCardVanish(int index, int type) {

            }

            @Override
            public void onItemClick(View cardImageView, int index) {
                Intent intent = newIntent(SlideGankMeiziActivity.this, meizis.get(index).getUrl(), meizis.get(index).getDesc());
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(SlideGankMeiziActivity.this,
                                mCardSlidePanel, PictureActivity.TRANSIT_PIC);
                // Android 5.0+
                try {
                    ActivityCompat.startActivity(SlideGankMeiziActivity.this, intent, optionsCompat.toBundle());
                } catch (Exception e) {
                    startActivity(intent);
                }
            }
        });
        presenter.loadMeiziList(1);
    }

    private static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.IMG_URL, url);
        intent.putExtra(PictureActivity.IMG_DESC, desc);
        return intent;
    }

    @Override
    protected void createPresenter() {
        presenter = new GankPresenter();
        presenter.attachView(this);
    }

    @Override
    public void showMeizi(GankListEntity gankListEntity) {
        if (gankListEntity == null
                || gankListEntity.getResults() == null
                || gankListEntity.getResults().size() == 0) {
            return;
        }
        meizis = gankListEntity.getResults();
        finishTask();
    }

    @Override
    public void showNoMore() {
        Toast.makeText(SlideGankMeiziActivity.this, "-------没有需要加载的数据-------", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(SlideGankMeiziActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据返回的妹子图片
     * 初始化卡片数据
     */
    private void finishTask() {
        CardDataItem dataItem;
        for (int i = 0; i < meizis.size(); i++) {
            dataItem = new CardDataItem();
            dataItem.detailed = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss").format(meizis.get(i).getCreatedAt());
            dataItem.imgUrl = meizis.get(i).getUrl();
            dataList.add(dataItem);
        }
        mCardSlidePanel.fillData(dataList);
    }
}
