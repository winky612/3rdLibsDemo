package com.zkhz.a3rdlibsdemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;

import org.greenrobot.eventbus.EventBus;

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
    private String input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        input = edtInput.getText().toString();



    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

        EventBus.getDefault().post(new MessageEvent(input));
        Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
        startActivity(intent);


    }
}
