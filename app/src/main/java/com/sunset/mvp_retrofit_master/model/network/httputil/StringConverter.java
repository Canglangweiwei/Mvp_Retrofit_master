package com.sunset.mvp_retrofit_master.model.network.httputil;

import com.sunset.mvp_retrofit_master.util.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * <p>
 * 封装有object转换成String的类
 * </p>
 * Created by weiwei on 2016/9/1
 */
@SuppressWarnings("ALL")
public class StringConverter implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        // 转换获取结果为string
        String result = value.string();
        // 过滤BOM头
        result = result.replaceAll("\ufeff", "");
        // 打印结果集
        LogUtil.logd("获取结果集：" + result);
        return result;
    }
}
