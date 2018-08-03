package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;

import com.wonderkiln.blurkit.BlurKit;
import com.zkhz.a3rdlibsdemo.rongyun.ChatroomKit;
import com.zkhz.a3rdlibsdemo.rongyun.DataInterface;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {

        return context;
    }

    public MyApplication() {
    }

    public MyApplication(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        BlurKit.init(this);

        ChatroomKit.init(this, DataInterface.appKey);


    }
}
