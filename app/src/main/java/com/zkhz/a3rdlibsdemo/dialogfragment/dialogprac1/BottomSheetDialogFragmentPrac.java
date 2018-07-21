package com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by wk on 2018/7/20 0020
 *
 * 用BottomSheetDialogFragment写AlertDialog  还是Alert  onCreateDialog的形式 写啥是啥
 *
 * onCreateView的形式 跟Fragment有关
 *
 *
 */
public class BottomSheetDialogFragmentPrac extends BottomSheetDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_view, null))
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
