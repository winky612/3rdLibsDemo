package com.zkhz.a3rdlibsdemo;

import android.os.Bundle;

import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
