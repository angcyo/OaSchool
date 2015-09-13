package com.angcyo.oaschool.mode.bean;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class MessageResult extends BaseResult {
    int tongzhinum = 0;
    int duanxinnum = 0;
    int emailnum = 0;

    public int getTongzhinum() {
        return tongzhinum;
    }

    public void setTongzhinum(int tongzhinum) {
        this.tongzhinum = tongzhinum;
    }

    public int getDuanxinnum() {
        return duanxinnum;
    }

    public void setDuanxinnum(int duanxinnum) {
        this.duanxinnum = duanxinnum;
    }

    public int getEmailnum() {
        return emailnum;
    }

    public void setEmailnum(int emailnum) {
        this.emailnum = emailnum;
    }
}
