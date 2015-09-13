package com.angcyo.oaschool.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angcyo.oaschool.R;
import com.angcyo.oaschool.mode.bean.TitleListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class TitleListRecycleAdapter extends RecyclerView.Adapter<TitleListRecycleAdapter.BaseRecycleViewHolder> {

    public static final int ITEM_TYPE_LOADING = 1;
    public static final int ITEM_TYPE_NORMAL = 0;

    private List<TitleListBean> datas;
    private Context context;
    private String loadTip = "";
    private boolean isLoad = false;

    private OnItemClick itemListener;

    public TitleListRecycleAdapter(Context content) {
        datas = new ArrayList<>();
        this.context = content;
        loadTip = content.getString(R.string.pull_up_load_tip);
    }

    public TitleListRecycleAdapter(Context context, List<TitleListBean> datas) {
        this.datas = datas;
        this.context = context;
    }

    public void resetLoadTip() {
        loadTip = context.getString(R.string.pull_up_load_tip);
        isLoad = false;
    }

    public void setLoadTip(String tip, boolean load) {
        setLoadTip(tip, load, false);
    }

    public void setLoadTip(String tip, boolean load, boolean refresh) {
        if (loadTip.equalsIgnoreCase(tip) && !refresh) {
            return;
        }
        loadTip = tip;
        isLoad = load;
        notifyItemChanged(datas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (isLastItem(position)) {//最后一项, 显示为加载更多
            return ITEM_TYPE_LOADING;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_title_item, parent, false);
        return new BaseRecycleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {
        if (getItemViewType(position) == ITEM_TYPE_LOADING) {
            holder.loadLayout.setVisibility(View.VISIBLE);
            holder.itemLayout.setVisibility(View.GONE);

            if (isLoad) {
                holder.loadBar.setVisibility(View.VISIBLE);
            } else {
                holder.loadBar.setVisibility(View.GONE);
            }
            holder.loadTip.setText(loadTip);
        } else {
            final TitleListBean bean = datas.get(position);
            holder.loadLayout.setVisibility(View.GONE);
            holder.itemLayout.setVisibility(View.VISIBLE);
            holder.author.setText(bean.getAuthor());
            holder.title.setText(bean.getTitle());
            holder.time.setText(bean.getDatetime());
            if (bean.getIsnew().equalsIgnoreCase("1")) {
                holder.author.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
                holder.title.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
            } else {
                holder.author.setTextColor(context.getResources().getColor(android.R.color.black));
                holder.title.setTextColor(context.getResources().getColor(android.R.color.black));
            }

            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemListener != null) {
                        itemListener.onItemClick(bean, position);
                    }
                }
            });
        }
    }

    private boolean isLastItem(int position) {
        return getItemCount() > 0 && (position + 1) >= getItemCount();
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.size() == 0) {
            return 0;
        }
        return datas.size() + 1;//+1 用于显示加载更多的视图
    }

//    public void addDatas(List<ContentItem> datas) {
//        this.datas.addAll(datas);
//        notifyDataSetChanged();
//    }

    public void setDatas(List<TitleListBean> datas, boolean append) {
        int itemCount = this.datas.size();
        this.datas = datas;
        if (append) {
            notifyItemRangeInserted(itemCount, datas.size() - itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    public void setDatas(List<TitleListBean> datas, int loadType, int itemCount) {
//        notifyDataSetChanged();
        this.datas = datas;
//            notifyDataSetChanged();
//            notifyItemRangeInserted(datas.size() - itemCount, itemCount);
    }

    public void setOnItemListener(OnItemClick itemListener) {
        this.itemListener = itemListener;
    }

    public interface OnItemClick {
        void onItemClick(TitleListBean bean, int position);
    }

    static class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.item_layout)
        LinearLayout itemLayout;
        @Bind(R.id.load_bar)
        ProgressBar loadBar;
        @Bind(R.id.load_tip)
        TextView loadTip;
        @Bind(R.id.load_layout)
        RelativeLayout loadLayout;

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
