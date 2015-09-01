package com.angcyo.oaschool.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class BaseRecycleAdapter extends RecyclerView.Adapter<BaseRecycleAdapter.BaseRecycleViewHolder> {

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
