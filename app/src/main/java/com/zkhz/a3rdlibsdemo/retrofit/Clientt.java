package com.zkhz.a3rdlibsdemo.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Clientt {
    private API api;

    private Clientt() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);

    }


    //返回Network对象
    private static class NetworkHolder {
        private static final Clientt sInstance = new Clientt();
    }

    public static API getAPI() {
        return Clientt.NetworkHolder.sInstance.api;
    }

}
