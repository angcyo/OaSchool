package com.angcyo.oaschool.control;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.event.UserEvent;
import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.mode.bean.UserResult;
import com.angcyo.oaschool.util.OkHttpClientManager;
import com.google.gson.Gson;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-12-012.
 * <p/>
 * 用户登录 的任务;
 */
public class UserTask extends TaskRunnable {

    private String userName;
    private String userPw;

    public UserTask(String userName, String userPw) {
        this.userName = userName;
        this.userPw = userPw;
    }

    private static String getUrl(String userName, String passWord) {
        String url = String.format("http://%s/APP/login.asp?userName=%s&password=%s", RConstant.SER_IP, userName, passWord);
        return url;
    }

    @Override
    public void run() {
        UserEvent event = new UserEvent();
        try {
            byte[] bytes = OkHttpClientManager.getAsBytes(getUrl(userName, userPw));
            String response = new String(bytes, "gbk");
            Gson gson = new Gson();
            UserResult userResult = gson.fromJson(response, UserResult.class);
            event.result = userResult;
            event.code = RConstant.CODE_OK;
        } catch (IOException e) {
            e.printStackTrace();
            event.code = RConstant.CODE_ER;
        }
        EventBus.getDefault().post(event);
    }
}
