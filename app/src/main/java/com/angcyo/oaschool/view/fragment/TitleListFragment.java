package com.angcyo.oaschool.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.angcyo.oaschool.ContentActivity;
import com.angcyo.oaschool.OaApplication;
import com.angcyo.oaschool.OaService;
import com.angcyo.oaschool.R;
import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.control.MessageTask;
import com.angcyo.oaschool.control.TitleTask;
import com.angcyo.oaschool.event.TitleEvent;
import com.angcyo.oaschool.mode.bean.DataControl;
import com.angcyo.oaschool.mode.bean.TitleListBean;
import com.angcyo.oaschool.mode.bean.TitleListResult;
import com.angcyo.oaschool.util.PopupTipWindow;
import com.angcyo.oaschool.util.Util;
import com.angcyo.oaschool.view.BaseFragment;
import com.angcyo.oaschool.view.adapter.DividerItemDecoration;
import com.angcyo.oaschool.view.adapter.TitleListRecycleAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class TitleListFragment extends BaseFragment implements TitleListRecycleAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener {

    public static String KEY_POS = "key_pos";

    int position = 0;//通知, 短信, 邮件
    @Bind(R.id.close)
    ImageButton close;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.empty_tip)
    TextView emptyTip;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;

    TitleListRecycleAdapter adapter;

    boolean isNoMore = false;//没有更多了
    boolean isAnimEnd = false;
    boolean isLoadEnd = false;
    TitleListResult titleResult;
    LinearLayoutManager linearLayoutManager;

    private int scrollState = -1;//保存recycle 滚动的状态, 未更改过滚动状态,-1
    private boolean isLastItem = true;//是否滚动到了最后一个item, 内容太少, 可显示数量超过内容的bug
    private boolean isRefreshing = false;//是否正在刷新
    private boolean isLoadingMore = false;//是否正在加载更多

    @Override
    protected void loadData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        position = getArguments().getInt(KEY_POS, 0);
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title_list, container, false);
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        adapter = new TitleListRecycleAdapter(getActivity());
        adapter.setOnItemListener(this);
        recycle.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollState = newState;
                onLoadMore();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                isLastItem = manager.findLastCompletelyVisibleItemPosition() >= manager.getItemCount() - 1;
                onLoadMore();
            }
        });
        refresh.setColorSchemeResources(R.color.action_bar_bg,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        refresh.setOnRefreshListener(this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        close.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tran_btot));
        Animation animator = AnimationUtils.loadAnimation(getActivity(), R.anim.tran_btot);
        animator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimEnd = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimEnd = true;
                updateRecycle(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        recycle.startAnimation(animator);
    }

    @Override
    protected void initAfter() {
        super.initAfter();
        loadData();
    }

    private void loadData() {
        if (Util.isNetOk(getActivity())) {
            isLoadEnd = false;
            isRefreshing = true;
            refresh.setRefreshing(true);
            OaService.addTask(new TitleTask(OaApplication.getUserInfo().appid));
        } else {
            isRefreshing = false;
            refresh.setRefreshing(false);
            PopupTipWindow.showTip(getActivity().getApplicationContext(), getActivity().getString(R.string.no_net_tip));
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(TitleEvent event) {
        if (event.loadMore) {
            isLoadingMore = false;
            adapter.resetLoadTip();
        } else {
            isRefreshing = false;
            refresh.setRefreshing(false);
        }

        if (event.code == RConstant.CODE_OK && event.result.getResult() == RConstant.CODE_OK) {//请求成功,并且返回成功
            isLoadEnd = true;
            titleResult = event.result;
            if (event.loadMore) {
                TitleTask.increment();//预加载下一页
            } else {
                isLastItem = true;
                TitleTask.resetPage();
            }
            updateRecycle(event.loadMore);
        } else {
            PopupTipWindow.showTip(getActivity().getApplicationContext(), getActivity().getString(R.string.happened_error));
            if (event.loadMore) {
                isLoadEnd = true;
                isLoadingMore = false;
                adapter.setLoadTip(getString(R.string.pull_up_load_tip), false, true);
            } else {
                isRefreshing = false;
                refresh.setRefreshing(false);
            }
        }
    }

    private void updateRecycle(boolean append) {
        if (isAnimEnd && isLoadEnd) {
            List<TitleListBean> datas = DataControl.getTitles(titleResult, append);
            if (append && DataControl.isNull(titleResult)) {
                isNoMore = true;
                adapter.setLoadTip(getString(R.string.no_more_tip), false);
            } else {
                isNoMore = false;
                adapter.setDatas(datas, append);
            }
            if (titleResult.getTitles().get(0).getGonggaoList().size() == 0 && DataControl.getTitles().size() == 0) {
                emptyTip.setVisibility(View.VISIBLE);
            } else {
                emptyTip.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        View rootView = getView();
        if (rootView != null) {
            rootView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_1to0));
        }
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(TitleListBean bean, int position) {
        if (Util.isEmpty(bean.getId())) {
            PopupTipWindow.showTip(getActivity(), "无效通知,请查证后查阅.");
            return;
        }
        DataControl.getTitles().get(position).setIsnew("0");
        adapter.setDatas(DataControl.getTitles(), false);

        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra(ContentActivity.KEY_ID, bean.getId());
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OaService.addTask(new MessageTask(OaApplication.getUserInfo().appid));
    }

    private void onLoadMore() {
        if (isLoadingMore) {
            return;
        }
        if (isLastItem) {
            if (isNoMore) {
                adapter.setLoadTip(getString(R.string.no_more_tip), false);
                return;
            }
            if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {//松手后
                scrollState = -1;//充值滚动状态
                adapter.setLoadTip(getString(R.string.loading), true);
                if (Util.isNetOk(getActivity())) {//网络OK
                    isLoadingMore = true;
                    OaService.addTask(new TitleTask(OaApplication.getUserInfo().appid, true));
                } else {
                    isLoadingMore = false;
                    adapter.setLoadTip(getString(R.string.check_net_tip), false);
                }
            } else if (scrollState != -1) {
                isLoadingMore = false;
                adapter.setLoadTip(getString(R.string.want_load_more), false);
            }
        } else {
            adapter.setLoadTip(getString(R.string.pull_up_load_tip), false);
        }
    }

    @Override
    public void onRefresh() {
        if (isRefreshing) {
            PopupTipWindow.showTip(getActivity(), getString(R.string.wait_refresh_tip));
            return;
        }
        loadData();
    }
}
