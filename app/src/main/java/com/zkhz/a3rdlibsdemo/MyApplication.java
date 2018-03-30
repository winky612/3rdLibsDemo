package com.zkhz.a3rdlibsdemo;

import android.app.Application;
import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
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
        UMConfigure.init(context,UMConfigure.DEVICE_TYPE_PHONE,Constants.UM_APP_KEY);
        UMConfigure.setLogEnabled(true);
    }

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }


}
