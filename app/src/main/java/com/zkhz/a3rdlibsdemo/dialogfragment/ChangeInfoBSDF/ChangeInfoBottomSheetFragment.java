package com.zkhz.a3rdlibsdemo.dialogfragment.ChangeInfoBSDF;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wk on 2018/7/22.
 */

public class ChangeInfoBottomSheetFragment extends BottomSheetDialogFragment {
    private static final String KEY_TITLE = "KEY_TITLE";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_input)
    EditText edtInput;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_no)
    Button btnNo;
    Unbinder unbinder;
    private String mTitle;
    private String mContent;


    private onChangeClickListener listener;

    public static ChangeInfoBottomSheetFragment newInstance(String title) {
        ChangeInfoBottomSheetFragment fragment = new ChangeInfoBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(KEY_TITLE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_change_info, null);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle.setText(mTitle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_ok, R.id.btn_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:

                mContent = edtInput.getText().toString();
                listener = (onChangeClickListener) getActivity();
                listener.onChangeClick(mContent);

                dismiss();

                break;
            case R.id.btn_no:

                dismiss();
                break;
        }
    }


    public interface onChangeClickListener {

        void onChangeClick(String content);
    }
}
