package com.angcyo.oaschool.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angcyo.oaschool.ContentActivity;
import com.angcyo.oaschool.OaService;
import com.angcyo.oaschool.R;
import com.angcyo.oaschool.components.RConstant;
import com.angcyo.oaschool.control.DownFileTask;
import com.angcyo.oaschool.event.DownEvent;
import com.angcyo.oaschool.util.PopupTipWindow;
import com.angcyo.oaschool.util.Util;
import com.angcyo.oaschool.view.BaseFragment;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class SaveFileFragment extends BaseFragment {

    public static String KEY_URL = "key_url";
    public static String KEY_FILE_NAME = "key_name";
    public static String KEY_FILE_SIZE = "key_size";
    public static String KEY_FILE_MIME_TYPE = "key_type";
    @Bind(R.id.save)
    Button save;
    @Bind(R.id.open)
    Button open;
    @Bind(R.id.close)
    Button close;
    @Bind(R.id.layout)
    RelativeLayout layout;
    @Bind(R.id.file_name)
    EditText fileName;
    @Bind(R.id.file_size)
    TextView fileSize;
    @Bind(R.id.file_layout)
    RelativeLayout fileLayout;

    String urlStr, fileNameStr, fileSizeStr, mimeType;
    boolean isOpen = false;//是否需要打开

    @Override
    protected void loadData(Bundle savedInstanceState) {
        urlStr = getArguments().getString(KEY_URL, "");
        fileNameStr = getArguments().getString(KEY_FILE_NAME, "");
        fileSizeStr = getArguments().getString(KEY_FILE_SIZE, "");
        mimeType = getArguments().getString(KEY_FILE_MIME_TYPE, "text/plain");
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_save_file, container, false);
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tran_btot));
        fileName.setText(fileNameStr);
        fileSize.setText(fileSizeStr);
    }

    @OnClick(R.id.close)
    public void onClose() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.save)
    public void onSave() {
        isOpen = false;
        fileNameStr = fileName.getText().toString();
        if (Util.isEmpty(fileNameStr)) {
            fileName.setError("请输入文件名");
            fileName.requestFocus();
            return;
        }

        ((ContentActivity) getActivity()).showDialogTip("正在保存,请稍等...");
        OaService.addTask(new DownFileTask(urlStr, fileNameStr, RConstant.filePath));
    }

    @OnClick(R.id.open)
    public void onOpen() {
        ((ContentActivity) getActivity()).showDialogTip("解析中,请稍等...");
        isOpen = true;
        OaService.addTask(new DownFileTask(urlStr, fileNameStr, RConstant.filePath));
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onDownEvent(DownEvent event) {
        ((ContentActivity) getActivity()).hideDialogTip();
        if (event.code == RConstant.CODE_ER) {
            PopupTipWindow.showTip(getActivity(), "文件下载失败");
            return;
        } else {
            if (isOpen) {
                openFile(event.fileFullPath, mimeType);
            } else {
                PopupTipWindow.showTip(getActivity(), "保存在:" + event.fileFullPath);
            }
        }
    }

    private void openFile(String fileName, String mimeType) {
        Intent i = new Intent();
        i.setAction(android.content.Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(new File(fileName)), mimeType);
        startActivity(i);
    }
}


