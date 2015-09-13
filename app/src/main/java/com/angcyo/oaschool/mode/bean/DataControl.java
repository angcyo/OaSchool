package com.angcyo.oaschool.mode.bean;

import com.angcyo.oaschool.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class DataControl {

    private static List<TitleListBean> titles = new ArrayList<>();

    public static List<TitleListBean> getTitles() {
        return titles;
    }

    public static boolean isNull(TitleListResult result) {
        boolean isNull = true;
        for (TitleListResult.TitlesEntity.GonggaoListEntity entity : result.getTitles().get(0).getGonggaoList()) {
            if (Util.isEmpty(entity.getId())) {
                continue;
            } else {
                isNull = false;
                break;
            }
        }
        return isNull;
    }

    public static List<TitleListBean> getTitles(TitleListResult result, boolean append) {
        List<TitleListBean> beans = new ArrayList<>();
        for (TitleListResult.TitlesEntity.GonggaoListEntity entity : result.getTitles().get(0).getGonggaoList()) {
            TitleListBean bean = new TitleListBean();
            if (Util.isEmpty(entity.getId())) {
                continue;
            }
            bean.setId(entity.getId());
            bean.setAuthor(entity.getAuthor());
            bean.setTitle(entity.getTitle());
            bean.setDatetime(entity.getDatetime());
            bean.setIsnew(entity.getIsnew());
            beans.add(bean);
        }

        if (append) {
            titles.addAll(beans);
        } else {
            titles = beans;
        }
        return getTitles();
    }
}
