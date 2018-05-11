package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

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

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
