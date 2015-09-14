package com.angcyo.oaschool.control;

import com.angcyo.oaschool.mode.bean.TitleListBean;
import com.angcyo.oaschool.mode.bean.TitleListResult;
import com.angcyo.oaschool.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class DataControl {

    private static List<TitleListBean> titles = new ArrayList<>();

    public static List<TitleListBean> getTitles() {
        Collections.sort(titles, new TitleListBean());//排序
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
            bean.setOntop(entity.getOntop());

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
