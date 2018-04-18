package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.presenter;

import android.util.Log;

import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.bean.UserBean;
import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.model.IUserModel;
import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.model.UserModel;
import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.view.IUserView;

/**
 * Created by Administrator on 2018/4/18 0018.
 *
 * (4)Presenter:它能拥有m和v层的接口实例
 * Presenter就能通过接口与View及Model进行交互了：
 * 主要就是save和load两个方法的具体逻辑，在两个方法中调用m和v层的接口中规定好的方法
 *
 */



//将带有具体方法实现的实例upcast成为接口
public class UserPresenter implements IPresenter {

    private IUserModel mUserModel;
    private IUserView mUserView;


    public UserPresenter(IUserView mUserView) {
        this.mUserView = mUserView;
        mUserModel=new UserModel();
    }

    //此方法将view层获取的数据存入model层，且只用到了接口里的方法
    @Override
    public void saveUser() {
        if (mUserView.getUserName()==null||mUserView.getUserPassword()==null){
            Log.d("test", "saveUser: 账号或者密码不能为空");
        }else {
            mUserModel.saveUser(mUserView.getId(),mUserView.getUserName(),mUserView.getUserPassword());
        }



    }

    @Override
    public void loadUser() {

        UserBean userBean=mUserModel.load(mUserView.getId());
        if (userBean!=null){
            mUserView.setUserName(userBean.getUser());
            mUserView.setUserPassword(userBean.getPwd());
        }else
            Log.d("test", "loadUser: 编号为id的用户数据不存在");

    }
}
