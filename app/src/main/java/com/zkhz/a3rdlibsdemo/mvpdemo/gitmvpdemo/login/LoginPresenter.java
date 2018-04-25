package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.login;

/**
 * Created by Administrator on 2018/4/25 0025.
 * <p>
 * presenter
 */

public interface LoginPresenter {

    void validateCredentials(String name,String password);

    void onDestroy();

}
