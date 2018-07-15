package com.zkhz.a3rdlibsdemo.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wk on 2018/7/15.
 */

public abstract class MyCallback<T> implements Callback<WebData<T>> {


    @Override
    public void onResponse(Call<WebData<T>> call, Response<WebData<T>> response) {
        String success = response.body().getSuccess();
        if (success.equals("B0001")){
            //shi bai
//                 onFailure(call,new Exception(response.body().msg));
            onBFailure(response.body().getMsg());
        }else {
            //cheng gong
            onSuccess(call,response);
        }

    }

    abstract void onSuccess(Call<WebData<T>> call, Response<WebData<T>> response);
    abstract void onBFailure(String msg);


    @Override
    public void onFailure(Call<WebData<T>> call, Throwable t) {

    }
}
