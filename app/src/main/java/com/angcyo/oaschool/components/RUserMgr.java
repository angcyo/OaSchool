package com.angcyo.oaschool.components;

import com.angcyo.oaschool.event.UserEvent;
import com.angcyo.oaschool.util.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-01-001.
 */
public class RUserMgr {
    private static String URL_LOGIN = "http://120.197.25.113:8123/APP/login.asp?userName=a02&password=a123456";

    //"http://demo2.yfidea.com/APP/login.asp?userName=a02&password=a123456";
    //"http://zx.ioa123.com/login.asp?userName=a02&password=a123456";
    //http://demo2.yfidea.com/APP/login.asp?userName=a02&password=a123456
    private static Thread thread;

    public static void userLogin(final String userName, final String passWord) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UserEvent userEvent = new UserEvent();
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(getLoginUrl(userName, passWord)).openConnection();
                    httpURLConnection.connect();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    String line = bufferedReader.readLine();
                    if (line.equalsIgnoreCase(UserEvent.CODE_OK + "")) {
                        userEvent.code = UserEvent.CODE_OK;
                        RConstant.userName = userName;
                    } else {
                        userEvent.code = UserEvent.CODE_ER;
                    }
                    EventBus.getDefault().post(userEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                    userEvent.code = UserEvent.CODE_ER;
                    EventBus.getDefault().post(userEvent);
                }
                thread = null;
            }
        });
        thread.start();
    }

    private static String getLoginUrl(String userName, String passWord) {
        String url = String.format("http://120.197.25.113:8123/APP/login.asp?userName=%s&password=%s", userName, passWord);
        Logger.e("登录服务器:" + url);
        return url;
    }
}
