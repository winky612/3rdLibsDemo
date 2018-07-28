package com.zkhz.a3rdlibsdemo.tab;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBar();

        //在界面未初始化之前调用的初始化窗口
        initWindows();

        //如果界面初始化某些数据成功(传入的数据时正确的) 走初始化控件 数据;若初始化失败,
        if (initArgs(getIntent().getExtras())){

            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initWidget();
            initData();
        }else {
            finish();
        }

    }

    protected void setStatusBar() {
        //系统状态栏颜色设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"),true);
            StatusBarCompat.setTranslucent(getWindow(), false);

        }
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * 初始化一些窗口数据
     */
    protected void initWindows(){

    }

    //常规Activity间数据传递---Bundle
    //初始化相关参数 如果参数正确,返回true
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    //protected---只要extends这个Activity就能复写这个方法
    //返回当前界面的资源文件id
    protected abstract int getContentLayoutId();


    /**
     * 初始化控件
     */
    protected void initWidget() {
        unbinder =  ButterKnife.bind(this);

        ImageView backIv = (ImageView) findViewById(R.id.iv_setting_back);
        if (backIv != null) {
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }


    /**
     * 初始化数据
     */
    protected void initData() {

    }



    @Override
    public boolean onSupportNavigateUp() {

        //当点击界面导航返回时,finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 设置标题栏
     * @param title
     */
    public void setToolBar(String title){
        TextView titleTv = (TextView) findViewById(R.id.tv_setting_title);

        if (titleTv != null){
            titleTv.setText(title);
        }
    }

}

