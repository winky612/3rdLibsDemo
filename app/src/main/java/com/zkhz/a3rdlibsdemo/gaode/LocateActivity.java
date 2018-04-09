package com.zkhz.a3rdlibsdemo.gaode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class LocateActivity extends AppCompatActivity {

    @BindView(R.id.tv_city)
    TextView tvCity;

    private LocHelper locHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //key b34e75c285989c6b85ea713ba0e14f85

        setContentView(R.layout.activity_locate);
        ButterKnife.bind(this);

        locHelper = new LocHelper();
        locHelper.setTextView(tvCity);
        locHelper.initLoca(LocateActivity.this);

    }

}
