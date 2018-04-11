package com.zkhz.a3rdlibsdemo.juherv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zkhz.a3rdlibsdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "NewsActivity";
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private NewsRVAdapter newsRVAdapter;
    private Call<Data> newsCall;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(NewsActivity.this,LinearLayoutManager.VERTICAL,false);
        rvNews.setLayoutManager(linearLayoutManager);
        newsRVAdapter=new NewsRVAdapter();
        rvNews.setAdapter(newsRVAdapter);



        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.d(TAG,message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("http://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        NewsApi newsApi=retrofit.create(NewsApi.class);
        newsCall= newsApi.getNewsData("top","93ba680b61c343b8a0777a045c0faab0");
        newsCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data=response.body();
                List<Data.ResultBean.DataBean> newsList=data.getResult().getData();
                
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });




    }


}
