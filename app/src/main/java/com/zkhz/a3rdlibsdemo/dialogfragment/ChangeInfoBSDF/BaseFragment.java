package com.zkhz.a3rdlibsdemo.dialogfragment.ChangeInfoBSDF;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wk on 2018/7/24 0024
 */
public abstract class BaseFragment extends Fragment {

    //涉及到复用问题,将root保存起来
    protected View mRoot;
    protected Unbinder mRootUnBinder;
    protected Activity mActivity;


    //当一个fragment被添加到Activity时,首先调用的是onAttach(),所以可在这个方法中进行初始化界面的一些操作
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //初始化参数
        initArgs(getArguments());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {

            int layoutId = getContentLayoutId();
            //初始化当前的跟布局,但是不在(attach to root:false)创建时就添加到container里面
            View root = inflater.inflate(layoutId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {

                //若fragment被回收,再重新初始化这个fragment时,有可能mRoot还没被回收,∴需要将当前root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }

        }


        return mRoot;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //当view创建完成之后初始化数据
        initData();

    }

    //protected---只要extends这个Activity就能复写这个方法
    //返回当前界面的资源文件id
    protected abstract int getContentLayoutId();


    //常规Activity间数据传递---Bundle
    protected void initArgs(Bundle bundle) {
    }


    /**
     * 初始化控件
     */
    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this, root);


    }


    /**
     * 初始化数据
     */
    protected void initData() {

    }


    /**
     * 返回按键触发时调用
     *
     * @return true---代表我已处理返回逻辑,Activity不用自己finish      false---代表我没处理逻辑,Activity自己走自己的逻辑.默认不拦截
     */
    public boolean onBackPress() {
        return false;
    }


}
