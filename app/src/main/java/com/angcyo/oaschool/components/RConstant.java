package com.angcyo.oaschool.components;

import com.angcyo.oaschool.util.Util;

import java.io.File;

/**
 * Created by angcyo on 15-08-24-024.
 */
public class RConstant {

    public static String SER_IP = "oa.nslhzx.com";//保存服务器地址,先从文件读取, 如果文件没有, 默认采用此IP;
    //    public static String SER_IP = "120.197.25.113:8123";//保存服务器地址,先从文件读取, 如果文件没有, 默认采用此IP;
    public static String SER_IP_FILE_PATH = "/mnt/sdcard/oaip.ini"; //默认IP文件路径: /mnt/sdcard/oaip.ini

    public static int POPTIP_TIME = 2000;//提示窗口消失的时间
    public static int POPTIP_OFFSET_Y = 200;//提示窗口消失的时间

    public static boolean useHeart = false; //是否启动心跳,每隔一定时间执行任务
    public static int HEART_TIME = 1000;//心跳时间

    public static String filePath = Util.getSDPath() + File.separator + "oaapp";//默认文件保存路径

    public static int COL_NUM = 4;//主界面 的列数
    public static int CODE_OK = 1;//登录成功
    public static int CODE_ER = 0;//登录失败
}
