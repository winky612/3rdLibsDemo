package com.zkhz.a3rdlibsdemo.gaode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class NaviActivity extends AppCompatActivity {
    @BindView(R.id.btn_guide)
    Button btnGuide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_locate);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_guide)
    public void onViewClicked() {

    }
}
