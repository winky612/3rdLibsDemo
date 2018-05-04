package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxjavaActivity8 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;
    @BindView(R.id.btn_day03)
    Button btnDay03;
    @BindView(R.id.btn_day04)
    Button btnDay04;
    @BindView(R.id.btn_day05)
    Button btnDay05;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02, R.id.btn_day03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:

                rxJava();
                break;
            case R.id.btn_day02:
                break;
            case R.id.btn_day03:
                break;
        }
    }

    /**
     * 发送128个事件没有问题是因为FLowable内部有一个大小为128的水缸, 超过128就会装满溢出来, 那既然你水缸这么小, 那我给你换一个大水缸如何?
     *
     */
    private void rxJava() {








    }
}
