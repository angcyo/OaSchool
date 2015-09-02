package com.angcyo.oaschool;

import android.widget.FrameLayout;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.view.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.container)
    FrameLayout container;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void initAfter() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        setTitle("当前用户:" + RConstant.userName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
