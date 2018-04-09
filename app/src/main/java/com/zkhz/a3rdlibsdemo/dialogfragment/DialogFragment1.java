package com.zkhz.a3rdlibsdemo.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by Administrator on 2018/4/9 0009.
 *
 * onCreateView即是用定义的xml布局文件展示Dialog
 */

public class DialogFragment1 extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.fragment_oncreateview,container);
        return view;
    }
}
