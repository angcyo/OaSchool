package com.angcyo.oaschool.mode;

/**
 * Created by angcyo on 15-08-27-027.
 */
public class ContentItem {
    String msgContent;
    String imgUrl;

    public ContentItem(String msgContent, String imgUrl) {
        this.msgContent = msgContent;
        this.imgUrl = imgUrl;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
