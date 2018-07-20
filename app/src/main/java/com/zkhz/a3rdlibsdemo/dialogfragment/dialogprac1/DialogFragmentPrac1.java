package com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by wk on 2018/7/20 0020
 *
 * 测试 横屏之后 onCreateView（）和onCreateDialog（）方式创建的Dialog  是否会崩 会保存输入框数据
 *
 */
public class DialogFragmentPrac1 extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_view,container);

        return view;
    }
}
