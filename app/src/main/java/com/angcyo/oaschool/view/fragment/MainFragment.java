package com.angcyo.oaschool.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angcyo.oaschool.MainActivity;
import com.angcyo.oaschool.OaApplication;
import com.angcyo.oaschool.OaService;
import com.angcyo.oaschool.R;
import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.components.RMainMenuMgr;
import com.angcyo.oaschool.control.MessageTask;
import com.angcyo.oaschool.event.MessageEvent;
import com.angcyo.oaschool.util.PopupTipWindow;
import com.angcyo.oaschool.util.Util;
import com.angcyo.oaschool.view.BaseFragment;
import com.angcyo.oaschool.view.adapter.MainRecycleAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-02-002.
 */
public class MainFragment extends BaseFragment implements MainRecycleAdapter.OnItemClick {

    @Bind(R.id.recycle)
    RecyclerView recycle;

    MainRecycleAdapter adapter;

    @Override
    protected void loadData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
//        if (Util.isNetOk(getActivity())) {
//            OaService.addTask(new MessageTask(OaApplication.getUserInfo().appid));
//        } else {
////            PopupTipWindow.showTip(getActivity().getApplicationContext(), getActivity().getString(R.string.no_net_tip));
//            PrettyToast.show(getActivity(), getActivity().getString(R.string.no_net_tip));
//        }
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        adapter = new MainRecycleAdapter(RMainMenuMgr.getMenuBeans());
        adapter.setOnItemListener(this);
        recycle.setLayoutManager(new GridLayoutManager(this.getActivity(), RConstant.COL_NUM));
        recycle.setAdapter(adapter);
        recycle.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(MessageEvent event) {
        if (event.code == RConstant.CODE_OK && event.result.getResult() == RConstant.CODE_OK) {
            if (event.result.getTongzhinum() > 0) {
                RMainMenuMgr.getMenuBeans().get(0).setTips(event.result.getTongzhinum());
                adapter.updateDataWithPosition(RMainMenuMgr.getMenuBeans(), 0);
            }
            if (event.result.getDuanxinnum() > 0) {
                RMainMenuMgr.getMenuBeans().get(1).setTips(event.result.getDuanxinnum());
                adapter.updateDataWithPosition(RMainMenuMgr.getMenuBeans(), 1);
            }
            if (event.result.getEmailnum() > 0) {
                RMainMenuMgr.getMenuBeans().get(2).setTips(event.result.getEmailnum());
                adapter.updateDataWithPosition(RMainMenuMgr.getMenuBeans(), 2);
            }
        } else {
            PopupTipWindow.showTip(getActivity().getApplicationContext(), getActivity().getString(R.string.need_relogin));
            ((MainActivity) getActivity()).gotoLogin(((MainActivity) getActivity()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.isNetOk(getActivity())) {
            OaService.addTask(new MessageTask(OaApplication.getUserInfo().appid));
        } else {
            PopupTipWindow.showTip(getActivity().getApplicationContext(), getActivity().getString(R.string.no_net_tip));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(int position) {// 点击菜单项的时候,调用
//        PopupTipWindow.showTip(getActivity(), "点击" + position);
        BaseFragment fragment = new TitleListFragment();
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                args.putInt(TitleListFragment.KEY_POS, position);
                break;
            case 1:
                args.putInt(TitleListFragment.KEY_POS, position);
                break;
            case 2:
                args.putInt(TitleListFragment.KEY_POS, position);
                break;
            default:
                PopupTipWindow.showTip(getActivity(), "功能开发中,请期待!");
                return;
        }
        fragment.setArguments(args);
        ((MainActivity) getActivity()).add(fragment);
    }
}
