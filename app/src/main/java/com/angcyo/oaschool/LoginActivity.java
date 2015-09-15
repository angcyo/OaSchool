package com.angcyo.oaschool;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.control.SchoolTask;
import com.angcyo.oaschool.control.UserTask;
import com.angcyo.oaschool.event.SchoolEvent;
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
    public static String KEY_PW_TIP = "key_pw_tip";
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_password)
    EditText userPassword;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.ver_code)
    TextView verCode;
    UserInfo userInfo;
    @Bind(R.id.pw_tip)
    TextView pwTip;
    @Bind(R.id.delete)
    ImageButton delete;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//        startService(new Intent("oaservice"));
        userInfo = new UserInfo();
        initWindow(R.color.action_bar_bg);
        verCode.setText("版本号:" + RConstant.VERSION);

        userPassword.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                return false;
            }
        });
        userPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return false;
            }
        });

        sendDelayRunnable(new Runnable() {
            @Override
            public void run() {
                if (pwTip != null) {
                    pwTip.setVisibility(View.GONE);
                }
            }
        }, RConstant.POPTIP_TIME);

    }

    @Override
    protected void initAfter() {
        userName.setText(Hawk.get(KEY_NAME, ""));
        userPassword.setText(Hawk.get(KEY_PW, ""));

        int tipCount = Hawk.get(KEY_PW_TIP, 1);
        if (tipCount > 3) {
            pwTip.setVisibility(View.GONE);
        } else {
            pwTip.setVisibility(View.VISIBLE);
            Hawk.put(KEY_PW_TIP, ++tipCount);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.isNetOk(this)) {
            OaService.addTask(new SchoolTask());
        } else {
            PopupTipWindow.showTip(this, getString(R.string.no_net_tip));
        }
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
            if (!RConstant.DATA_VERSION.equalsIgnoreCase(event.schoolResult.getDataversion())) {
                PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_INFO, "此版本已经过期，请升级再使用！");
            } else {
                userInfo.schoolName = event.schoolResult.getSchoolname();
                userInfo.appid = event.result.getAppid();
                userInfo.tname = event.result.getTname();
                OaApplication.setUserInfo(userInfo);
                launchActivity(MainActivity.class);
                finish();
            }
        } else {
            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_FAILED, "登录失败");
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onSchoolEvent(SchoolEvent event) {
        if (event.code == RConstant.CODE_OK) {
            setTitle(event.schoolResult.getSchoolname());
            if (!RConstant.DATA_VERSION.equalsIgnoreCase(event.schoolResult.getDataversion())) {
                PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_INFO, "此版本已经过期，请升级再使用！");
            }
        } else {
            PopupTipWindow.showTip(this, PopupTipWindow.ICO_TYPE_FAILED, "无法获取学校名称");
        }
    }

    @OnClick(R.id.delete)
    public void onDelete() {
        userPassword.setText("");
        Hawk.remove(KEY_PW);
    }
}
