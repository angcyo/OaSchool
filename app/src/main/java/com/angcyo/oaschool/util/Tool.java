package com.angcyo.oaschool.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by angcyo on 15-08-24-024.
 */
public class Tool {

    static String config = getSDPath() + File.separator + "config.ini";

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0)
            return true;
        return false;
    }

    /**
     * 返回SD卡路径,如果没有返回默认的下载缓存路径
     *
     * @return the sD path
     */
    public static String getSDPath() {
        return isExternalStorageWritable() ? Environment
                .getExternalStorageDirectory().getPath() : Environment
                .getDownloadCacheDirectory().getPath();
    }

    /**
     * 判断是否有SD卡
     *
     * @return the boolean
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 从配置文件中,获取值
     *
     * @param key    the key
     * @param config the config
     * @return the string
     */
    public static String get(String key, String config) {
        FileInputStream s;
        String value = "";
        try {
            s = new FileInputStream(config);
            Properties properties = new Properties();
            properties.load(s);
            value = properties.getProperty(key, "");
            s.close();
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * 保存到配置文件 格式: key!value
     *
     * @param content the content
     * @param config  the config
     */
    public static void set(String content, String config) {
        Properties prop = new Properties();
        String[] values;
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(config);
            fos = new FileOutputStream(config);
            prop.load(fis);
            values = content.split("!");
            String value = values.length == 1 ? "" : values[1].trim();
            prop.setProperty(values[0].trim(), value);
            prop.storeToXML(fos, " by angcyo", "utf-8");
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception e2) {
            }
        }
    }

    public String get(String key) {
        return get(key, config);
    }

    public void set(String content) {
        set(content, config);
    }

}
