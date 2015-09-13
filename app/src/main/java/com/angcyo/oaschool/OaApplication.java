package com.angcyo.oaschool;

import android.app.Application;
import android.content.Intent;

import com.angcyo.oaschool.mode.UserInfo;
import com.angcyo.oaschool.util.OkioUtil;
import com.angcyo.oaschool.util.Util;
import com.orhanobut.hawk.Hawk;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class OaApplication extends Application {

    private static UserInfo userInfo;//如果已经登录,直接进入

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        OaApplication.userInfo = userInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        OkioUtil.writeToFile("应用程序创建" + Util.callMethodAndLine());
        startService(new Intent("oaservice"));
        userInfo = new UserInfo();
    }
}
