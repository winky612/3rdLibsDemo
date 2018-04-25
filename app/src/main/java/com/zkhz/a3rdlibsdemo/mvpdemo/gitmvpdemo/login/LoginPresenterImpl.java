package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.login;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String name, String password) {

        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(name, password, new LoginInteractor.onLoginFishedListener() {
            @Override
            public void onUserNameError() {
                if (loginView != null) {
                    loginView.setUserNameError();
                    loginView.hideProgress();
                }
            }

            @Override
            public void onPasswordError() {
                if (loginView != null) {
                    loginView.setPasswordError();
                    loginView.hideProgress();
                }

            }

            @Override
            public void onSuccess() {

                loginView.navigateToHome();

            }
        });

    }

    @Override
    public void onDestroy() {
        loginView = null;

    }
}
