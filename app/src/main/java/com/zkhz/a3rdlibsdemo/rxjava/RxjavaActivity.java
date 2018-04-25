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
import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class RxjavaActivity extends AppCompatActivity {
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


    @OnClick({R.id.btn_day01, R.id.btn_day02, R.id.btn_day03, R.id.btn_day04, R.id.btn_day05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:
                rxjava_day01();
                break;
            case R.id.btn_day02:
                break;
            case R.id.btn_day03:
                break;
            case R.id.btn_day04:
                break;
            case R.id.btn_day05:
                break;
        }
    }

    private void rxjava_day01() {

        //创建被观察者Observable(上游 事件产生者)
        Observable<Integer>


    }
}
