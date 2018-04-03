package com.zkhz.a3rdlibsdemo.loading;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;
import com.zkhz.a3rdlibsdemo.R;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class LoadingActivity extends AppCompatActivity {

    private ImageView wechat, qq, sina, wechat2, qq2, sina2;
    private TextView txt;
    private UMImage image;
    private UMWeb web;
    private UMShareAPI shareAPI;

    private ProgressDialog dialog;
    private boolean isAuth;

    private BaseResp resp = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        wechat = findViewById(R.id.wechat);
        qq = findViewById(R.id.qq);
        sina = findViewById(R.id.sina);
        wechat2 = findViewById(R.id.wechat2);
        qq2 = findViewById(R.id.qq2);
        sina2 = findViewById(R.id.sina2);
        txt=findViewById(R.id.txt);

        shareAPI=UMShareAPI.get(LoadingActivity.this);

        dialog=new ProgressDialog(LoadingActivity.this);

        isAuth = UMShareAPI.get(LoadingActivity.this).isAuthorize(LoadingActivity.this, SHARE_MEDIA.WEIXIN);

        initMedia();

        //其中123是requestcode，可以根据这个code判断，用户是否同意了授权。如果没有同意，可以根据回调进行相应处理：
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                            Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                            Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }


        //第三方登录
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load(LoadingActivity.this,SHARE_MEDIA.WEIXIN,authListener);

                getUserInfo(LoadingActivity.this,SHARE_MEDIA.WEIXIN,authListener);


            }
        });

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load(LoadingActivity.this,SHARE_MEDIA.QQ,authListener);

                getUserInfo(LoadingActivity.this,SHARE_MEDIA.QQ,authListener);



            }
        });


        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //分享
        wechat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shareAPI.isInstall(LoadingActivity.this, SHARE_MEDIA.WEIXIN);

                new ShareAction(LoadingActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(image)
                        .setCallback(umShareListener)//回调监听器
                        .share();

            }
        });


        qq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(LoadingActivity.this)
                        .withMedia(web)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(umShareListener)
                        .share();

            }
        });

        sina2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    //获取用户信息
    private void getUserInfo(Activity activity, SHARE_MEDIA platform, UMAuthListener listener) {
        shareAPI.getPlatformInfo(activity, platform, listener);


    }

    //第三方授权
    private void load(Context context,SHARE_MEDIA platform,UMAuthListener listener) {

        if (isAuth) {
            UMShareAPI.get(context).deleteOauth(LoadingActivity.this, platform, listener);
        } else {
            UMShareAPI.get(context).doOauthVerify(LoadingActivity.this, platform, listener);
        }

    }

    private void initMedia() {

        //图片 资源文件
        image = new UMImage(LoadingActivity.this, R.drawable.red);

        //链接
        String url="https://baike.baidu.com/item/%E9%BB%84%E7%8E%AB%E7%91%B0/55429?fr=aladdin";
        web = new UMWeb(url);
        web.setTitle("This is web title");//标题
        web.setThumb(new UMImage(LoadingActivity.this,R.drawable.umeng_socialize_sina));  //缩略图
        web.setDescription("my description");//描述
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //第三方分享
    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(LoadingActivity.this, "startttt", Toast.LENGTH_SHORT).show();

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(LoadingActivity.this, "分享成功的回调 成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(LoadingActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(LoadingActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    //第三方授权
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
//            startActivity(new Intent(LoadingActivity.this, MainActivity.class));

            String temp = "";
            for (String key : data.keySet()) {
                temp = temp + key + " : " + data.get(key) + "\n";
            }
            txt.setText(temp);

            Toast.makeText(LoadingActivity.this, "第三方授权 成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoadingActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoadingActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        UMShareAPI.get(this).release();
    }
}
