package com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wk on 2018/7/15.
 */

public abstract class MyCallback<T extends WebData> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        onSuccess(call, response);
    }


    protected abstract void onSuccess(Call<T> call, Response<T> response);

}
