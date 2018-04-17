package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class PracMVPDemoActivity extends AppCompatActivity {
    @BindView(R.id.edt_id)
    EditText edtId;
    @BindView(R.id.edt_fn)
    EditText edtFn;
    @BindView(R.id.edt_ln)
    EditText edtLn;
    @BindView(R.id.btn_write)
    Button btnWrite;
    @BindView(R.id.btn_read)
    Button btnRead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracmvp_demo);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_write, R.id.btn_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                break;
            case R.id.btn_read:
                break;
        }
    }
}
