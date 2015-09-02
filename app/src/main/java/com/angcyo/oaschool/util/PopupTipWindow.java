package com.angcyo.oaschool.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.angcyo.oaschool.R;

/**
 * Created by angcyo on 15-09-01-001.
 */
public class PopupTipWindow {
    public static final int ICO_TYPE_NOICO = 0;//无图标
    public static final int ICO_TYPE_SUCCEED = 1;//打钩图标
    public static final int ICO_TYPE_FAILED = 2;//打叉图标
    public static final int ICO_TYPE_INFO = 3;//感叹号图标
    private static WindowManager wManager;
    private static WindowManager.LayoutParams wmParams;
    private static TextView rootLayout;

    private static long START_SHOW_TIME = 0l;//窗口开始显示的时间
    private static long END_SHOW_TIME = 0l;//隐藏的时间

    static Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                END_SHOW_TIME = System.currentTimeMillis();
                if ((END_SHOW_TIME - START_SHOW_TIME) >= Constant.POPTIP_TIME) {
                    wManager.removeView(rootLayout);
                    rootLayout = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void showTip(Context context, String tip) {
        showTip(context, ICO_TYPE_NOICO, tip);
    }

    public static void showTip(Context context, int icoType, String tip) {
        //得到窗口管理
        if (wManager == null) {
            wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        //初始化窗口参数
        if (wmParams == null) {
            wmParams = new WindowManager.LayoutParams();
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
            wmParams.format = 1;
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            wmParams.gravity = Gravity.RIGHT | Gravity.TOP;
            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.x = 0;
            wmParams.y = 300;
            wmParams.windowAnimations = R.style.PopupTipWindowAnim;
        }

        //创建提示布局
        if (rootLayout == null) {
            TextView textView = new TextView(context);
            textView.setText(tip);
            textView.setPadding(20, 10, 10, 10);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setSingleLine();
            textView.setBackgroundResource(android.support.design.R.color.accent_material_light);
            rootLayout = textView;
            wManager.addView(rootLayout, wmParams);
        }

        //更新view
        switch (icoType) {
            case ICO_TYPE_SUCCEED:
                rootLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gou, 0, 0, 0);
                rootLayout.setTextColor(Color.YELLOW);
                break;
            case ICO_TYPE_FAILED:
                rootLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cha, 0, 0, 0);
                rootLayout.setTextColor(Color.RED);
                break;
            case ICO_TYPE_INFO:
                rootLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.info, 0, 0, 0);
                rootLayout.setTextColor(Color.WHITE);
                break;
            case ICO_TYPE_NOICO:
                rootLayout.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                rootLayout.setTextColor(Color.WHITE);
            default:
                break;
        }

        //回调
        rootLayout.removeCallbacks(hideRunnable);
        START_SHOW_TIME = System.currentTimeMillis();
        rootLayout.postDelayed(hideRunnable, Constant.POPTIP_TIME);
        rootLayout.setText(" " + tip);
    }

    public static void removeTip() {
        try {
            rootLayout.removeCallbacks(hideRunnable);
            wManager.removeView(rootLayout);
            rootLayout = null;
            wManager = null;
            wmParams = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
