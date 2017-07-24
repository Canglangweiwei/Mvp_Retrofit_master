package com.sunset.mvp_retrofit_master.config;

import android.app.Application;

import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.util.LogUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * 作者：weiwei on 2016/12/2 11:57
 */
public class MVPApplication extends Application {

    public static MVPApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        // 初始化Logger
        onLogger();
        // 页面字体
        initTypeFace();
    }

    /**
     * 初始化Log日志
     */
    private void onLogger() {
        LogUtil.logInit(Constants.LOG_DEBUG);
    }

    /**
     * 初始化页面字体
     */
    private void initTypeFace() {
        CalligraphyConfig calligraphyConfig = new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setDefaultFontPath("fonts/dqht.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }
}
