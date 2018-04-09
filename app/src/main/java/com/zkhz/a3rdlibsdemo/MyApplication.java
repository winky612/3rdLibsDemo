package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;

import com.wonderkiln.blurkit.BlurKit;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class MyApplication extends Application {

    public static Context context;

    public MyApplication() {

    }

    public MyApplication(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        BlurKit.init(this);
    }
}
