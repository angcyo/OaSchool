package com.angcyo.oaschool.control;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.event.TitleEvent;
import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.mode.bean.TitleListResult;
import com.angcyo.oaschool.util.OkHttpClientManager;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class TitleTask extends TaskRunnable {
    public static int CUR_PAGE = 1; //从第一页开始
    private String appid;
    private boolean loadMore;

    public TitleTask(String appid) {
        this.appid = appid;
        loadMore = false;
    }

    public TitleTask(String appid, boolean loadMore) {
        this.appid = appid;
        this.loadMore = loadMore;
    }

    public static void resetPage() {
        CUR_PAGE = 1;
    }

    public static int increment() {
        return ++CUR_PAGE;
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    private String getUrl() {
        String url = String.format("http://%s/APP/TongZhiList.asp?APPID=%s&curpage=%d", RConstant.SER_IP, appid, CUR_PAGE);
        return url;
    }

    private String getUrl(int page) {
        String url = String.format("http://%s/APP/TongZhiList.asp?APPID=%s&curpage=%d", RConstant.SER_IP, appid, page);
        return url;
    }

    @Override
    public void run() {
        TitleEvent event = new TitleEvent();
        try {
            event.loadMore = isLoadMore();
            String url = getUrl(isLoadMore() ? (CUR_PAGE + 1) : 1);
            byte[] bytes = OkHttpClientManager.getAsBytes(url);
            String response = new String(bytes, "gbk");
            response = response.substring(response.indexOf("<body>") + 6, response.indexOf("</body>")).trim();
            Gson gson = new Gson();
            TitleListResult result = gson.fromJson(response, TitleListResult.class);
            event.result = result;
            event.code = RConstant.CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            event.code = RConstant.CODE_ER;
        }
        EventBus.getDefault().post(event);
    }
}
