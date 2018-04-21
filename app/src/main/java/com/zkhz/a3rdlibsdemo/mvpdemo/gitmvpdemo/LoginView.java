package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo;

/**
 * view层描述
 *
 * 负责显示数据、提供友好界面跟用户交互就行
 *
 */

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setUserNameError();
    void setPasswordError();
    void navigateToHome();

}
