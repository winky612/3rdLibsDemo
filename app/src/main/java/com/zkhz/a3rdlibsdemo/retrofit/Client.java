package com.zkhz.a3rdlibsdemo.retrofit;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Client {

//    private MyService api;
    private API api;
    private static final String TAG = "";

    private Client() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(API.class);
//        api = retrofit.create(MyService.class);
    }


    //返回Network对象
    private static class NetworkHolder {
        private static final Client sInstance = new Client();
    }

    //返回api，以便获取api接口中的方法
    public static API getAPI() {
        return NetworkHolder.sInstance.api;
    }

//
//    //返回api，以便获取api接口中的方法
//    public static MyService getAPI() {
//        return NetworkHolder.sInstance.api;
//    }
}
