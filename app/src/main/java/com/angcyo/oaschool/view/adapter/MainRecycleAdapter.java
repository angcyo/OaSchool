package com.angcyo.oaschool.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angcyo.oaschool.R;
import com.angcyo.oaschool.mode.bean.MainMenuBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.BaseRecycleViewHolder> {

    private List<MainMenuBean> menus;

    private OnItemClick itemListener;

    public MainRecycleAdapter(List<MainMenuBean> menus) {
        this.menus = menus;
    }

    /**
     * 更新指定位置的数据
     *
     * @param position the position
     */
    public void updateDataWithPosition(List<MainMenuBean> menus, int position) {
        this.menus = menus;
        notifyItemChanged(position);
    }

    public void setOnItemListener(OnItemClick itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecycleViewHolder(View.inflate(parent.getContext(), R.layout.adapter_main_item, null));
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {
        MainMenuBean menuBean = menus.get(position);
        holder.img.setImageResource(menuBean.getIco());
        holder.title.setText(String.valueOf(menuBean.getTitle()));
        if (menuBean.getTips() == 0) {
            holder.tip.setVisibility(View.GONE);
        } else {
            holder.tip.setVisibility(View.VISIBLE);
            holder.tip.setText(String.valueOf(menuBean.getTips()).length() > 2 ? "99+" : String.valueOf(menuBean.getTips()));
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    static class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.item_layout)
        RelativeLayout itemLayout;
        @Bind(R.id.tip)
        TextView tip;

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
