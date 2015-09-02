package com.angcyo.oaschool.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by angcyo on 15-08-31-031.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = loadView(inflater, container, savedInstanceState);
        initView(view);
        initAfter();
        return view;
    }

    protected abstract void loadData(Bundle savedInstanceState);

    protected abstract View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initView(View rootView);

    protected void initAfter() {
    }
}
