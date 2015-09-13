package com.angcyo.oaschool.components;

import com.angcyo.oaschool.R;
import com.angcyo.oaschool.mode.bean.MainMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class RMainMenuMgr {
    private static List<MainMenuBean> menuBeans = new ArrayList<>();

    public static List<MainMenuBean> getMenuBeans() {
        if (menuBeans.size() <= 0) {
            menuBeans.add(new MainMenuBean(R.drawable.notify, "通知", 0));
            menuBeans.add(new MainMenuBean(R.drawable.sms, "短信", 0));
            menuBeans.add(new MainMenuBean(R.drawable.email, "邮件", 0));
        }
        return menuBeans;
    }
}
