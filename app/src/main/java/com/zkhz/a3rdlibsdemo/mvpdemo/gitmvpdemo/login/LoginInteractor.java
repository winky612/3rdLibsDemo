package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.login;

/**
 * Model
 *
 * Interactor的作用实际上就是获取Model(从本地数据库，或者是服务器),转换成ViewModel，回调通知把ViewModel传递给Presenter。
 */

public interface LoginInteractor {

    interface onLoginFishedListener{
        void onUserNameError();
        void onPasswordError();
        void onSuccess();

    }

    void login(String name,String password,onLoginFishedListener listener);
}
