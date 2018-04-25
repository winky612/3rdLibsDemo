package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main.DemoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.progress)
    ProgressBar progress;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter=new LoginPresenterImpl(this,new LoginInteractorImpl());

    }

    @OnClick(R.id.button)
    public void onViewClicked() {

        loginPresenter.validateCredentials(username.getText().toString(),password.getText().toString());
    }

    @Override
    public void showProgress() {

        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        progress.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setUserNameError() {

        username.setError(getString(R.string.username_error));

    }

    @Override
    public void setPasswordError() {

        password.setError(getString(R.string.password_error));

    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, DemoActivity.class));

    }
}
