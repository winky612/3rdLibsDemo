package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class RxjavaLoginActivity3 extends AppCompatActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        register();

    }

    private void register() {

        //如果是一个新用户, 必须先注册, 等注册成功之后再自动登录该怎么做呢.
        final Api api = RxjavaRetrofitActivity2.getRetrofit().create(Api.class);
        api.getRegisterData("")                               // 发起注册请求
                .subscribeOn(Schedulers.io())               // 指定上游发送事件的线程 (在IO线程进行网络请求)
                .observeOn(AndroidSchedulers.mainThread())  // 回到主线程去处理请求注册结果
                .doOnNext(new Consumer<RegisterData>() {
                    @Override
                    public void accept(RegisterData registerData) throws Exception {

                        //先根据注册的响应结果去做一些操作
                    }
                })
                .observeOn(Schedulers.io())                  //回到IO线程去发起登录请求
                .flatMap(new Function<RegisterData, ObservableSource<LoginData>>() {
                    @Override
                    public ObservableSource<LoginData> apply(RegisterData registerData) throws Exception {
                        return api.getLoginData("");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())    //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {

                        Toast.makeText(RxjavaLoginActivity3.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(RxjavaLoginActivity3.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
