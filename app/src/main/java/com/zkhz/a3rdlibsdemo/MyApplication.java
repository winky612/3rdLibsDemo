package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

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

        //友盟 初始化common库
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        Config.DEBUG=true;
        UMConfigure.init(context,UMConfigure.DEVICE_TYPE_PHONE,null);
        UMConfigure.setLogEnabled(true);
    }

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac","507680dd82ab352da3381e26df0719bb");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }
}
