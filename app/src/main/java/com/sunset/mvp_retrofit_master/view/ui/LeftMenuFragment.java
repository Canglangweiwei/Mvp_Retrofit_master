package com.sunset.mvp_retrofit_master.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.event.Event;
import com.sunset.mvp_retrofit_master.util.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>
 * 左侧菜单
 * </p>
 * Created by weiwei on  2016/7/22
 */
@SuppressWarnings("ALL")
public class LeftMenuFragment extends Fragment {

    @Bind(R.id.right_slide_close)
    ImageView rightSlideClose;
    @Bind(R.id.search)
    ImageView search;
    @Bind(R.id.news_tv)
    TextView newsTv;
    @Bind(R.id.meizi_tv)
    TextView meiziTv;
    @Bind(R.id.video_tv)
    TextView videoTv;
    @Bind(R.id.story_tv)
    TextView story_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        loadView();
    }

    // 存储页面控件
    private List<View> mViewList;

    /**
     * 页面加载
     */
    private void loadView() {
        if (mViewList == null) {
            mViewList = new ArrayList<>();
        }
        mViewList.clear();
        mViewList.add(newsTv);
        mViewList.add(meiziTv);
        mViewList.add(videoTv);
        mViewList.add(story_tv);
    }

    @OnClick({R.id.right_slide_close,
            R.id.search, R.id.news_tv,
            R.id.meizi_tv, R.id.video_tv,
            R.id.story_tv})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_slide_close:// 关闭
                RxBus.getInstance().postEvent(new Event(1000, "closeMenu"));
                break;
            case R.id.search:   // 搜索
                break;
            case R.id.news_tv:  // 新闻
                RxBus.getInstance().postEvent(new Event(1000, "closeMenu"));
                break;
            case R.id.meizi_tv: // 干货
                Intent intent1 = new Intent(getActivity(), GankActivity.class);
                startActivity(intent1);
                break;
            case R.id.video_tv: // 视频
                Intent intent2 = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent2);
                break;
            case R.id.story_tv:// 故事
                Intent intent3 = new Intent(getActivity(), StoryActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    /**
     * 启动动画
     */
    public final void startAnim() {
        // 为搜索按钮设置动画
        startIconAnim(search);
        // 为关闭按钮设置动画
        startIconAnim(rightSlideClose);
        // 针对页面内容设置动画
        startColumnAnim();
    }

    /**
     * 页面内容动画
     */
    private void startColumnAnim() {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(0F, 0.0F, 0.0F, 0.0F);
        localTranslateAnimation.setDuration(700L);
        for (int i = 0; i < mViewList.size(); i++) {
            View localView = this.mViewList.get(i);
            localView.startAnimation(localTranslateAnimation);
            localTranslateAnimation = new TranslateAnimation(i * -35, 0.0F, 0.0F, 0.0F);
            localTranslateAnimation.setDuration(800L);
        }
    }

    /**
     * 为控件按钮设置加载动画
     */
    private void startIconAnim(View paramView) {
        ScaleAnimation localScaleAnimation =
                new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, paramView.getWidth() / 2,
                        paramView.getHeight() / 2);
        localScaleAnimation.setDuration(1200L);
        paramView.startAnimation(localScaleAnimation);
        float f1 = paramView.getWidth() / 2;
        float f2 = paramView.getHeight() / 2;
        localScaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, f1, f2);
        localScaleAnimation.setInterpolator(new BounceInterpolator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
