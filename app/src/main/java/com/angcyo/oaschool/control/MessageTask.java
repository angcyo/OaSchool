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

    private static String URL_MESSAGE = "http://120.197.25.113:8123/APP/TongZhiNew.asp?APPID=21016297992703899629405";

    private String appid;

    public MessageTask(String appid) {
        this.appid = appid;
    }

    private String getUrl() {
        String url = String.format("http://120.197.25.113:8123/APP/TongZhiNew.asp?APPID=%s", appid);
        return url;
    }
            /*返回类型....竟然不是json
            <!DOCTYPE html>
            <html>
            <head>
            <title>OAAPP</title>
            </head>
            <body>
                    {"result":1,"tongzhinum":16,"duanxinnum":5,"emailnum":3}
            </body>
            </html>*/

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
