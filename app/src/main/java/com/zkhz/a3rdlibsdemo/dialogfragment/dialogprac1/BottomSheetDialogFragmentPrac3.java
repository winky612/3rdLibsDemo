package com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by wk on 2018/7/20 0020
 */
public class BottomSheetDialogFragmentPrac3 extends BottomSheetDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_view, null);
        dialog.setContentView(view);
        return dialog;
    }
}
