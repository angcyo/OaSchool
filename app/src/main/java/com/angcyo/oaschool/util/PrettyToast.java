package com.angcyo.oaschool.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angcyo.oaschool.R;


/**
 * Created by angcyo on 15-08-25-025.
 */
public class PrettyToast {
    public static void show(Context context, String msg) {
        new ToastConfig(context, msg).getToast().show();
    }

    public static void showL(Context context, String msg) {
        new ToastConfig(context, msg).setDuration(Toast.LENGTH_LONG).getToast().show();
    }

    public static void showS(Context context, String msg) {
        new ToastConfig(context, msg).setDuration(Toast.LENGTH_SHORT).getToast().show();
    }

    public static void showY(Context context, String msg, int yOffset) {
        new ToastConfig(context, msg).setYOffset(yOffset).getToast().show();
    }

    static class ToastConfig {
        String msg;
        int msgCol;
        int duration;
        int yOffset;
        Context context;

        public ToastConfig(Context context, String msg) {
            this.msg = msg;
            this.context = context;
            msgCol = Color.WHITE;
            yOffset = 300;
            duration = Toast.LENGTH_LONG;
        }

        public ToastConfig setYOffset(int offset) {
            yOffset = offset;
            return this;
        }

        public ToastConfig setDuration(int lg) {
            duration = lg;
            return this;
        }

        public Toast getToast() {
            Toast toast = new Toast(context);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setBackgroundResource(R.drawable.toast_bg);
            linearLayout.setPadding(0, 10, 0, 10);
            linearLayout.setMinimumWidth(Integer.MAX_VALUE);

            TextView textView = new TextView(context);
            textView.setText(msg);
            textView.setTextColor(msgCol);
            textView.setGravity(Gravity.CENTER);
            //textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.logo, 0, 0, 0);
            linearLayout.addView(textView);

            toast.setGravity(Gravity.TOP, 0, yOffset);
            toast.setDuration(duration);
            toast.setView(linearLayout);
            return toast;
        }
    }
}
