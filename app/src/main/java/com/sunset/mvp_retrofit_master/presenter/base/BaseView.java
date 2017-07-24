package com.sunset.mvp_retrofit_master.presenter.base;

/**
 * <p>
 * 基本视图
 * </p>
 * Created by weiwei on 2016/7/25
 */
public interface BaseView {

    void showLoading();

    void dismissLoading();

    /**
     * 加载数据出错是调用
     * @param errorMsg 错误信息
     */
    void showLoadFailureMsg(String errorMsg);
}
