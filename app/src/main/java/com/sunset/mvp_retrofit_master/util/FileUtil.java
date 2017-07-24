package com.sunset.mvp_retrofit_master.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 文件工具类
 * </p>
 * Created by weiwei on 2016/7/25
 */
@SuppressWarnings("ALL")
public class FileUtil {

    public static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static final String ADPATH = FileUtil.SDPATH + "/Geecity_MVP";

    public static void createSdDir() {
        File file = new File(FileUtil.ADPATH);
        if (!file.exists()) {
            boolean create = file.mkdirs();
            Log.d("FileUtil", "create = " + create);
        } else {
            if (!file.isDirectory()) {
                file.delete();
                file.mkdir();
            }
        }
    }

    public static boolean isFileExist(String paramString) {
        if (paramString == null)
            return false;
        File localFile = new File(ADPATH + "/" + paramString);
        if (localFile.exists()) {
            return true;
        }
        return false;
    }

    public static File createFile(String fileName) throws IOException {
        File file = new File(ADPATH, fileName);
        file.createNewFile();
        return file;
    }

    public static List<String> getAllAD() {
        File file = new File(FileUtil.ADPATH);
        File[] fileList = file.listFiles();
        List<String> list = new ArrayList<>();
        if (null != fileList) {
            for (File f : fileList) {
                list.add(f.getAbsolutePath());
            }
        }
        return list;
    }
}
