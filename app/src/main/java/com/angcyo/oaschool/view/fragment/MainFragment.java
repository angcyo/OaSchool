package com.angcyo.oaschool.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angcyo.oaschool.R;
import com.angcyo.oaschool.view.BaseFragment;

/**
 * Created by angcyo on 15-09-02-002.
 */
public class MainFragment extends BaseFragment {

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initView(View rootView) {

    }
}
