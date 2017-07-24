package com.sunset.mvp_retrofit_master.model.network.httputil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * 功能描述：拦截器
 * </p>
 * Created by weiwei on 2017/6/2 0002.
 */
@SuppressWarnings("ALL")
public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request request = builder.addHeader("Content-type", "application/json").build();
        return chain.proceed(request);
    }
}
