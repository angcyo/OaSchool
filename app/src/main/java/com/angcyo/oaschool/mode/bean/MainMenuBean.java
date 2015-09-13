package com.angcyo.oaschool.mode.bean;

import android.support.annotation.DrawableRes;

import com.angcyo.oaschool.R;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class MainMenuBean {
    @DrawableRes
    private int ico;//图标样式
    private String title;//标题
    private int tips;//通知数量

    public MainMenuBean() {
        ico = R.drawable.logo;
        title = "标题";
        tips = 0;
    }

    public MainMenuBean(@DrawableRes int ico, String title, int tips) {
        this.ico = ico;
        this.title = title;
        this.tips = tips;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }
}
