package com.angcyo.oaschool.control;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.event.DownEvent;
import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.util.OkioUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-09-14-014.
 */
public class DownFileTask extends TaskRunnable {
    String url, fileName, filePath;

    public DownFileTask(String url, String fileName, String filePath) {
        this.url = url;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        DownEvent event = new DownEvent();
        try {
            event.fileFullPath = OkioUtil.writeToFileFromUrl(url, filePath, fileName);
            event.code = RConstant.CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            event.code = RConstant.CODE_ER;
        }
        EventBus.getDefault().post(event);
    }
}
