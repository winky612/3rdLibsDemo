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

/**
 * Flowable:
 * 之前我们所的上游和下游分别是Observable和Observer, 这次不一样的是上游变成了Flowable, 下游变成了Subscriber,
 * 但是水管之间的连接还是通过subscribe()
 */

public class RxjavaActivity7 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:


                break;
            case R.id.btn_day02:
                break;
        }
    }
}
