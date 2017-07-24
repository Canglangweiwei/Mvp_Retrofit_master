package com.sunset.mvp_retrofit_master.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * <p>
 * 基类
 * </p>
 * Created by weiwei on 2017/6/8 0008.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * calligraphy（高效加载字体包）使用方法
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public final void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("数据加载中，请稍后...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        if (!isFinishing()) {
            progressDialog.show();
        }
    }

    public final void cancelDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected abstract void initView();

    protected abstract void createPresenter();
}
