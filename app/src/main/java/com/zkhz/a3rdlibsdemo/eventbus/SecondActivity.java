package com.zkhz.a3rdlibsdemo.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.tv_txt)
    EditText tvTxt;

    private String input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        input=tvTxt.getText().toString();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new MessageEvent(input));

        finish();
    }
}
