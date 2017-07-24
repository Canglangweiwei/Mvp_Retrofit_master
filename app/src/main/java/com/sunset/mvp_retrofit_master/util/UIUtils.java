package com.sunset.mvp_retrofit_master.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.sunset.mvp_retrofit_master.config.MVPApplication;

/**
 * <p>
 * 处理工具
 * </p>
 * Created by weiwei on 2016/7/25
 */
@SuppressWarnings("ALL")
public class UIUtils {

    private static int screenWidth;

    private static int screenHeight;

    private static float density;

    private static void setScreenInfo() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) MVPApplication.sApp.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        density = dm.density;
    }

    public static int getScreenWidth() {
        if (screenWidth == 0)
            setScreenInfo();
        return screenWidth;
    }

    public static int getScreenHeight() {
        if (screenHeight == 0)
            setScreenInfo();
        return screenHeight;
    }

    public static float getDensity() {
        if (density == 0.0f)
            setScreenInfo();
        return density;
    }

    public static int dip2px(int dipValue) {
        float reSize = MVPApplication.sApp.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static int px2dip(int pxValue) {
        float reSize = MVPApplication.sApp.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, MVPApplication.sApp.getResources().getDisplayMetrics());
    }

    public static int length(String paramString) {
        int i = 0;
        for (int j = 0; j < paramString.length(); j++) {
            if (paramString.substring(j, j + 1).matches("[Α-￥]")) {
                i += 2;
            } else {
                i++;
            }
        }

        if (i % 2 > 0) {
            i = 1 + i / 2;
        } else {
            i = i / 2;
        }

        return i;
    }
}
