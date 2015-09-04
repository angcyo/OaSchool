package com.angcyo.oaschool.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angcyo.oaschool.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.BaseRecycleViewHolder> {

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecycleViewHolder(parent.inflate(parent.getContext(), R.layout.adapter_main_item, null));
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.notify);
        holder.title.setText("标题");
        holder.tip.setText("100");
    }

    @Override
    public int getItemCount() {
        return 14;
    }

    static class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.tip)
        TextView tip;

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
