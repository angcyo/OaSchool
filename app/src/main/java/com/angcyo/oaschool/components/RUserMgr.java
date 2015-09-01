package com.angcyo.oaschool.components;

import com.angcyo.oaschool.event.UserEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-01-001.
 */
public class RUserMgr {
    private static String URL_LOGIN = "http://demo2.yfidea.com/APP/login.asp?userName=a02&password=a123456"; //"http://zx.ioa123.com/login.asp?userName=a02&password=a123456";
    //http://demo2.yfidea.com/APP/login.asp?userName=a02&password=a123456
    private static Thread thread;

    public static void userLogin() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UserEvent userEvent = new UserEvent();
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URL_LOGIN).openConnection();
                    httpURLConnection.connect();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    String line = bufferedReader.readLine();
                    if (line.equalsIgnoreCase(UserEvent.CODE_OK + "")) {
                        userEvent.code = UserEvent.CODE_OK;
                    } else {
                        userEvent.code = UserEvent.CODE_ER;
                    }
                    EventBus.getDefault().post(userEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                    userEvent.code = UserEvent.CODE_ER;
                    EventBus.getDefault().post(userEvent);
                }
            }
        });
        thread.start();
    }
}
