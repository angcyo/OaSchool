package com.angcyo.oaschool;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.TextView;
=======
>>>>>>> origin/master

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.control.UserTask;
import com.angcyo.oaschool.event.UserEvent;
import com.angcyo.oaschool.mode.UserInfo;
import com.angcyo.oaschool.util.PopupTipWindow;
import com.angcyo.oaschool.util.Util;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-01-001.
 */
public class LoginActivity extends BaseActivity {
    public static String KEY_NAME = "key_name";
    public static String KEY_PW = "key_pw";
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_password)
    EditText userPassword;
    @Bind(R.id.login)
    Button login;
<<<<<<< HEAD
    @Bind(R.id.ver_code)
    TextView verCode;
=======
>>>>>>> origin/master
    UserInfo userInfo;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//        startService(new Intent("oaservice"));
        userInfo = new UserInfo();
        initWindow(R.color.action_bar_bg);
<<<<<<< HEAD
        verCode.setText("版本号:" + Util.getAppVersionName(this));
=======
>>>>>>> origin/master
    }

    @Override
    protected void initAfter() {
        userName.setText(Hawk.get(KEY_NAME, ""));
        userPassword.setText(Hawk.get(KEY_PW, ""));
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

        userInfo.userName = userName;
        userInfo.pw = userPw;

        if (Util.isNetOk(this)) {
            showDialogTip(getString(R.string.logining));
            OaService.addTask(new UserTask(userName, userPw));//登录用户
        } else {
            PopupTipWindow.showTip(this, getString(R.string.no_net_tip));
        }

        Hawk.put(KEY_NAME, userName);
        Hawk.put(KEY_PW, userPw);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onUserEvent(UserEvent event) {
        hideDialogTip();
        if (event.code == RConstant.CODE_OK && event.result.getResult() == RConstant.CODE_OK) {
//            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_SUCCEED, "登录成功");
            userInfo.appid = event.result.getAppid();
            userInfo.tname = event.result.getTname();
            OaApplication.setUserInfo(userInfo);
            launchActivity(MainActivity.class);
            finish();
        } else {
            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_FAILED, "登录失败");
        }
    }
<<<<<<< HEAD
=======

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        PopupTipWindow.();
        ButterKnife.unbind(this);
    }
>>>>>>> origin/master
}
