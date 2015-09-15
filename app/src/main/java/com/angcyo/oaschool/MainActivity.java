package com.angcyo.oaschool;

import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.angcyo.oaschool.mode.UserInfo;
import com.angcyo.oaschool.view.BaseFragment;
import com.angcyo.oaschool.view.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        initWindow(R.color.action_bar_bg);
    }

    @Override
    protected void initAfter() {
        replace(new MainFragment());
        setTitle("用户:" + OaApplication.getUserInfo().tname);
    }

    public void replace(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void add(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.tran_btot, R.anim.tran_ttob);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    public void gotoLogin(BaseActivity activity) {
        launchActivity(LoginActivity.class);
        activity.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.quit) {
            OaApplication.setUserInfo(new UserInfo());
            gotoLogin(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
