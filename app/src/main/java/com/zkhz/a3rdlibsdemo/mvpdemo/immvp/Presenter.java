package com.zkhz.a3rdlibsdemo.mvpdemo.immvp;

import android.text.TextUtils;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class Presenter implements IPresenter {


    private IView mView;

    //Presenter与View 绑定
    public Presenter(IView mView) {
        this.mView = mView;
    }

    @Override
    public void search() {

        //开启界面loading
        String inputString=mView.getInputString();
        if (TextUtils.isEmpty(inputString)){
            //为空直接返回
            return;
        }

        int hashCode=inputString.hashCode();
        IUserService service=new UserService();
        String serviceResult=service.search(hashCode);

        //假装对数据进行了处理
        String result="Result:"+inputString+"-"+serviceResult;

        //得到数据之后还可以进行一些列逻辑处理  eg.关闭界面loading

        //把值设置回View中
        mView.setResultString(result);

    }
}
