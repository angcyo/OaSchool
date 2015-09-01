package com.angcyo.oaschool.control;

import android.os.AsyncTask;

import com.angcyo.oaschool.mode.ContentItem;

import java.util.List;

/**
 * Created by angcyo on 15-08-27-027.
 */
public abstract class HttpAsync extends AsyncTask<String, Void, List<ContentItem>> {

    @Override
    protected List<ContentItem> doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(List<ContentItem> contentItems) {
        super.onPostExecute(contentItems);
        onPostExe(contentItems);
    }

    public abstract void onPostExe(List<ContentItem> contentItems);

}
