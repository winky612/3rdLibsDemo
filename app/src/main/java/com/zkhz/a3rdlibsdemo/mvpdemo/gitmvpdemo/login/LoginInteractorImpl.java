package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.login;


import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(final String name, final String password, final onLoginFishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(name)){
                    listener.onUserNameError();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    return;
                }

                listener.onSuccess();

            }
        },2000);

    }
}
