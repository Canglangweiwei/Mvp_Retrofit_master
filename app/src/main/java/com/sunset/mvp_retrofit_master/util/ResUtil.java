package com.sunset.mvp_retrofit_master.util;

import android.content.Context;

@SuppressWarnings("ALL")
public class ResUtil {
    public static int dp2px(Context context, float dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5);
    }
}
