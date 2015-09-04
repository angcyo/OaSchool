package com.angcyo.oaschool.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angcyo.oaschool.R;
import com.angcyo.oaschool.view.BaseFragment;
import com.angcyo.oaschool.view.adapter.MainRecycleAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-09-02-002.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);

        recycle.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        recycle.setAdapter(new MainRecycleAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
