package com.angcyo.oaschool.control;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.event.SchoolEvent;
import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.mode.bean.SchoolResult;
import com.angcyo.oaschool.util.OkHttpClientManager;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-12-012.
 * <p/>
 * 获取学校名 的任务;
 */
public class SchoolTask extends TaskRunnable {

    private static String getInfoUrl() {//学校信息
        String url = String.format("http://%s/APP/schoolname.asp", RConstant.SER_IP);
        return url;
    }

    @Override
    public void run() {
        SchoolEvent event = new SchoolEvent();
        try {
            byte[] bytes = OkHttpClientManager.getAsBytes(getInfoUrl());
            String response = new String(bytes, "gbk");
            response = response.substring(response.indexOf("<body>") + 6, response.indexOf("</body>")).trim();
            Gson gson = new Gson();
            SchoolResult schoolResult = gson.fromJson(response, SchoolResult.class);
            event.schoolResult = schoolResult;
            event.code = RConstant.CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            event.code = RConstant.CODE_ER;
        }
        EventBus.getDefault().post(event);
    }
}
