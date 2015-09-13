package com.angcyo.oaschool.event;

import com.angcyo.oaschool.mode.bean.TitleListResult;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class TitleEvent extends BaseEvent {
    public TitleListResult result;
    public boolean loadMore = false;
}
