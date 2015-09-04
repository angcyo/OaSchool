package com.angcyo.oaschool;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.angcyo.oaschool.components.RUserMgr;
import com.angcyo.oaschool.event.UserEvent;
import com.angcyo.oaschool.util.PopupTipWindow;
import com.angcyo.oaschool.util.Util;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-01-001.
 */
public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_password)
    EditText userPassword;
    @Bind(R.id.login)
    Button login;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initAfter() {
        //测试专用
        userName.setText("a02");
        userPassword.setText("a123456");
    }

    @OnClick(R.id.login)
    public void login(View view) {
        String userName = this.userName.getText().toString();
        String userPw = this.userPassword.getText().toString();
        if (Util.isEmpty(userName)) {
            this.userName.setError(getString(R.string.user_not_empty));
            this.userName.requestFocus();
            return;
        }
        if (Util.isEmpty(userPw)) {
            this.userPassword.setError(getString(R.string.password_not_empty));
            this.userPassword.requestFocus();
            return;
        }

        showDialogTip("登录中...");
        RUserMgr.userLogin(userName, userPw);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onUserEvent(UserEvent event) {
        hideDialogTip();
        if (event.code == UserEvent.CODE_OK) {
//            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_SUCCEED, "登录成功");
            launchActivity(MainActivity.class);
            finish();
        } else {
            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_FAILED, "登录失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        PopupTipWindow.removeTip();
        ButterKnife.unbind(this);
    }
}
