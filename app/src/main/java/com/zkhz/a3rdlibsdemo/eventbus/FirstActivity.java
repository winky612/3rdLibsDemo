package com.zkhz.a3rdlibsdemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class FirstActivity extends AppCompatActivity {
    @BindView(R.id.edt_input)
    EditText edtInput;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_fir)
    TextView tvFir;
    @BindView(R.id.btn_to3)
    Button btnTo3;
    private String input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @OnClick({R.id.btn_submit,R.id.btn_to3})
    public void onViewClicked(View view) {

        switch (view.getId()){
            case R.id.btn_submit:
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_to3:
                Intent intent2 = new Intent(FirstActivity.this, ThirdActivity.class);
                startActivity(intent2);
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String msg = event.getMsg();
        tvFir.setText(msg);
        String msg2=edtInput.getText().toString();
        msg=msg2;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
