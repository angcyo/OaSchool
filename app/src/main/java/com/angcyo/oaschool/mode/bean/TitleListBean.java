package com.angcyo.oaschool.mode.bean;

import java.util.Comparator;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class TitleListBean implements Comparator<TitleListBean> {

    /**
     * id : 267
     * author : 魏勉
     * title : vvvvvvvvvvvvvvvvvvvv
     * datetime : 9-11
     * isnew : 1
     */

    private String id;
    private String author;
    private String title;
    private String datetime;
    private String isnew;
    private String ontop;

    public String getOntop() {
        return ontop;
    }

    public void setOntop(String ontop) {
        this.ontop = ontop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }


    @Override
    public int compare(TitleListBean lhs, TitleListBean rhs) {
        if (lhs.getOntop().equalsIgnoreCase("1")) {
            return -1;
        }
        if (rhs.getOntop().equalsIgnoreCase("1")) {
            return 1;
        }
        return 0;
    }
}
