package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class RxjavaRetrofitActivity extends AppCompatActivity {

//    private Call<NewsData> call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
//        call=api.getNewsData("","")
        api.getNewsData("top","93ba680b61c343b8a0777a045c0faab0")
                .subscribeOn(Schedulers.io())                //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())   //回到主线程去处理请求结果
                .subscribe(new Observer<NewsData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsData value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(RxjavaRetrofitActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                        Toast.makeText(RxjavaRetrofitActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    }
                });




    }
}
