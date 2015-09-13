package com.angcyo.oaschool;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.angcyo.oaschool.util.Util;
import com.bumptech.glide.Glide;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//状态栏
        ImageView rootView = new ImageView(this);
        rootView.setImageResource(R.drawable.welcome_bg);
        rootView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(R.drawable.welcome_bg).into(rootView);
        setContentView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void initAfter() {
        sendDelayRunnable(new Runnable() {
            @Override
            public void run() {
                if (Util.isEmpty(OaApplication.getUserInfo().appid)) {
                    launchActivity(LoginActivity.class);
                } else {
                    launchActivity(MainActivity.class);
                }
                finish();
            }
        }, 1000);
    }
}
