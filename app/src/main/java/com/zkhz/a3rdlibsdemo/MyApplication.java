package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class MyApplication extends Application {

    private Context context;

    public MyApplication() {
    }

    public MyApplication(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
//        BlurKit.init(this);
    }
}
