package com.zkhz.a3rdlibsdemo.bugly;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wk on 2018/5/3.
 */

public class BuglyDemoActivity extends AppCompatActivity {
    @BindView(R.id.btn_bugly)
    Button btnBugly;
    @BindView(R.id.tv_txt)
    TextView tvTxt;
    @BindView(R.id.edt_wd)
    EditText edtWd;

    //App ID  a907047b3a   App Key  1ba62169-c3bc-4ddd-8c15-a9be5ef6294b
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buglydemo);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_bugly)
    public void onViewClicked() {

        CrashReport.testJavaCrash();
    }
}
