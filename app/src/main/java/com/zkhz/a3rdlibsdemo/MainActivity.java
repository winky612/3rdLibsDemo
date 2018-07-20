package com.zkhz.a3rdlibsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.dialogfragment.DialogFragment1;
import com.zkhz.a3rdlibsdemo.dialogfragment.dialogprac1.DialogFragmentPrac2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_input_view)
    Button edtInput;
    @BindView(R.id.btn_input_dialog)
    Button btnInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_input_view, R.id.btn_input_dialog})
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
        }
    }
}
