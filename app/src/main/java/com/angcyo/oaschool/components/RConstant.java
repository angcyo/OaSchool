package com.angcyo.oaschool.components;

/**
 * Created by angcyo on 15-08-24-024.
 */
public class RConstant {
    public static String APPKEY = "9d558e4ce598";
    public static String APPSECRET = "c281b6a71445ec898136e65b21e3f851";

    public static int POPTIP_TIME = 2000;//提示窗口消失的时间
    public static int POPTIP_OFFSET_Y = 200;//提示窗口消失的时间

    public static boolean useHeart = false; //是否启动心跳,每隔一定时间执行任务
    public static int HEART_TIME = 1000;//心跳时间

    public static int COL_NUM = 4;//主界面 的列数

    public static int CODE_OK = 1;//登录成功
    public static int CODE_ER = 0;//登录失败
}
