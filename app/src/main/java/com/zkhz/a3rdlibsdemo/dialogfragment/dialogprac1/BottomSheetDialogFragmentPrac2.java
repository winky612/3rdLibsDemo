package com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wk on 2018/7/20 0020
 */
public class BottomSheetDialogFragmentPrac2 extends BottomSheetDialogFragment {

    @BindView(R.id.edt_input_view)
    EditText edtInputView;
    Unbinder unbinder;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private OnBtnClickListener listener;
    private String edt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {


        // TODO: 2018/7/21 0021 点击的时候 获取！！
        edt = edtInputView.getText().toString();

        listener = (OnBtnClickListener) getActivity();
        if (listener != null) {
            listener.onBtnClick(edt);
        }

        dismiss();

    }

    public interface OnBtnClickListener {

        void onBtnClick(String edt);

    }

}
