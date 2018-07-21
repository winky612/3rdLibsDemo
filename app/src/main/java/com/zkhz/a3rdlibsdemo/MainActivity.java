package com.zkhz.a3rdlibsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.dialogfragment.DialogFragment1;
import com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1.BottomSheetDialogFragmentPrac;
import com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1.BottomSheetDialogFragmentPrac2;
import com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1.BottomSheetDialogFragmentPrac3;
import com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1.DialogFragmentPrac2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomSheetDialogFragmentPrac2.OnBtnClickListener {

    @BindView(R.id.btn_input_view)
    Button edtInput;
    @BindView(R.id.btn_input_dialog)
    Button btnInput2;
    @BindView(R.id.btn_bottom_dialog)
    Button btnBottomDialog;
    @BindView(R.id.btn_bottom_view)
    Button btnBottomView;
    @BindView(R.id.btn_bottom_dialog2)
    Button btnBottomDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_input_view, R.id.btn_input_dialog, R.id.btn_bottom_dialog, R.id.btn_bottom_view, R.id.btn_bottom_dialog2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_input_view:

                DialogFragment1 dialog_view = new DialogFragment1();
                dialog_view.show(getFragmentManager(), "view_dialog");
                break;
            case R.id.btn_input_dialog:
                DialogFragmentPrac2 dialog = new DialogFragmentPrac2();
                dialog.show(getFragmentManager(), "dialog");
                break;
            case R.id.btn_bottom_dialog:
                BottomSheetDialogFragmentPrac dialog_bottom = new BottomSheetDialogFragmentPrac();
                dialog_bottom.show(getSupportFragmentManager(), "dialog_bottom");
                break;
            case R.id.btn_bottom_view:
                BottomSheetDialogFragmentPrac2 dialog_view_bottom = new BottomSheetDialogFragmentPrac2();
                dialog_view_bottom.show(getSupportFragmentManager(), "dialog_view_bottom");

                break;

            case R.id.btn_bottom_dialog2:
                BottomSheetDialogFragmentPrac3 bottom_dialog = new BottomSheetDialogFragmentPrac3();
                bottom_dialog.show(getSupportFragmentManager(), "bottom_dialog");
                break;


        }
    }


    @Override
    public void onBtnClick(String edt) {

        Toast.makeText(this, edt+"", Toast.LENGTH_SHORT).show();

    }
}
