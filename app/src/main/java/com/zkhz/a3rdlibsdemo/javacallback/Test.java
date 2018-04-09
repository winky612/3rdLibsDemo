package com.zkhz.a3rdlibsdemo.javacallback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class Test extends AppCompatActivity {

    private Li li;
    private Wang wang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        li=new Li();
        wang=new Wang(li);

        wang.askQues("1+1=?");


    }
}
