package com.sunset.mvp_retrofit_master.model.network.httputil;

import com.sunset.mvp_retrofit_master.util.FileUtil;
import com.sunset.mvp_retrofit_master.util.LogUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Request;

/**
 * <p>
 * 图片下载
 * </p>
 * Created by weiwei on 2017/6/22
 */
public class OkHttpImageDownloader {

    /**
     * 图片下载
     *
     * @param url 图片地址
     */
    public static void download(String url) throws IOException {

        final Request request = new Request.Builder().url(url).build();

        HttpUtils.client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                LogUtil.logd("OkHttpImageDownloader", e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response)throws IOException  {
                FileUtil.createSdDir();
                String url = response.request().url().toString();
                int index = url.lastIndexOf("/");
                String pictureName = url.substring(index + 1);
                if (FileUtil.isFileExist(pictureName)) {
                    return;
                }
                LogUtil.logd("OkHttpImageDownloader", "图片名称：" + pictureName);
                FileOutputStream fos = new FileOutputStream(FileUtil.createFile(pictureName));
                InputStream in = response.body().byteStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                in.close();
                fos.close();
            }
        });
    }
}
