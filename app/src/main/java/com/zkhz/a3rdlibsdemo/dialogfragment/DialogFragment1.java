package com.zkhz.a3rdlibsdemo.dialogfragment;

import android.app.Dialog;
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
 *
 *
 * 您想让一部分 UI 在某些情况下显示为对话框，但在其他情况下全屏显示或显示为嵌入式片段（也许取决于设备使用大屏幕还是小屏幕）。
 * DialogFragment 类便具有这种灵活性，因为它仍然可以充当嵌入式 Fragment。
 * 但在这种情况下，您不能使用 AlertDialog.Builder 或其他 Dialog 对象来构建对话框。
 * 如果您想让 DialogFragment 具有嵌入能力，则必须在布局中定义对话框的 UI，然后在 onCreateView() 回调中加载布局。
 */


public class DialogFragment1 extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_oncreateview,container,false);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);        //去掉标题

        return dialog;
    }
}
