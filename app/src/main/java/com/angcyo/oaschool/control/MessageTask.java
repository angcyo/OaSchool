package com.angcyo.oaschool.control;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.event.MessageEvent;
import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.mode.bean.MessageResult;
import com.angcyo.oaschool.util.OkHttpClientManager;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-12-012.
 * 获取消息的 任务
 */
public class MessageTask extends TaskRunnable {
    private String appid;

    public MessageTask(String appid) {
        this.appid = appid;
    }

    private String getUrl() {
        String url = String.format("http://%s/APP/TongZhiNew.asp?APPID=%s", RConstant.SER_IP, appid);
        return url;
    }

    @Override
    public void run() {
        MessageEvent event = new MessageEvent();
        try {
            String response = OkHttpClientManager.getAsString(getUrl());
            response = response.substring(response.indexOf("<body>") + 6, response.indexOf("</body>")).trim();
            Gson gson = new Gson();
            MessageResult result = gson.fromJson(response, MessageResult.class);
            event.code = RConstant.CODE_OK;
            event.result = result;
        } catch (Exception e) {
            e.printStackTrace();
            event.code = RConstant.CODE_ER;
        }
        EventBus.getDefault().post(event);
    }
}
