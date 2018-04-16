package com.zkhz.a3rdlibsdemo.recyclerview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //在界面未初始化之前调用的初始化窗口
        initWindows();

        //如果界面初始化某些数据成功(传入的数据时正确的) 走初始化控件 数据;若初始化失败,
        if (initArgs(getIntent().getExtras())){

            getContentLayoutId();
            initWidget();
            initData();
        }else {
            finish();
        }

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

        ButterKnife.bind(this);


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
     * 假如activity有好多fragment,点击返回键,不想finish整个页面,想一层一层跳出
     */
    @Override
    public void onBackPressed() {

        //得到当前Activity下的所有Fragment
        List<Fragment> fragments=getSupportFragmentManager().getFragments();
        if (fragments!=null && fragments.size()>0){
            for (Fragment fragment:fragments) {
                //判断是否为我们能处理的Fragment类型
                if (fragment instanceof BaseFragment){
                    //判断是否拦截了返回键
                    if (((BaseFragment) fragment).onBackPress()){
                        //如果有,直接return
                        return;
                    }
                }
                
            }
        }
        super.onBackPressed();
        finish();
    }
}

