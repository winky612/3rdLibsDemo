package com.zkhz.a3rdlibsdemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zkhz.a3rdlibsdemo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class RetrofitActivity extends AppCompatActivity {

    private Call<String> call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call=Client.getAPI().getDataCall();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });






        Clientt.getAPI().getDataCall().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }
}
