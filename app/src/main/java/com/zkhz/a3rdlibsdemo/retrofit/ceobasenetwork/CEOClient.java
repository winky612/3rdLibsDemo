package com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork;

import android.text.TextUtils;
import android.util.Log;

import com.zkhz.a3rdlibsdemo.retrofit.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CEOClient {

    private static final String TAG = "CEOClient";
    private volatile static CEOClient instance;
    private API api;


    private CEOClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "log: "+message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originRequest = chain.request();
                        JSONObject object = null;
                        String dataParamter = null;
                        switch (originRequest.method()) {
                            case "GET":
                                HttpUrl httpUrl = originRequest.url();

                                for (String name : httpUrl.queryParameterNames()) {
                                    if (object == null) {
                                        object = new JSONObject();
                                    }
                                    try {
                                        object.put(name, httpUrl.queryParameter(name));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                dataParamter = object == null ? null : object.toString();
                                if (!TextUtils.isEmpty(dataParamter)) {
                                    HttpUrl newUrl = new HttpUrl.Builder()
                                            .scheme(httpUrl.scheme())
                                            .host(httpUrl.host())
                                            .encodedPath(httpUrl.encodedPath())
                                            .addQueryParameter("data", dataParamter).build();
                                    return chain.proceed(originRequest.newBuilder()
                                            .url(newUrl)
                                            .build());
                                }
                                break;
                            case "POST":
                                RequestBody originBody = originRequest.body();
                                if (null != originBody && originBody instanceof FormBody) {
                                    FormBody originFormBody = (FormBody) originBody;
                                    for (int i = 0; i < originFormBody.size(); i++) {
                                        if (null == object) {
                                            object = new JSONObject();
                                        }
                                        try {
                                            object.put(originFormBody.name(i), originFormBody.value(i));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    dataParamter = object == null ? null : object.toString();
                                    if (!TextUtils.isEmpty(dataParamter)) {
                                        FormBody newFormBody = new FormBody.Builder()
                                                .add("data", dataParamter)
                                                .build();
                                        return chain.proceed(originRequest.newBuilder().post(newFormBody)
                                                .build());
                                    }
                                }
                                break;
                        }
                        return chain.proceed(chain.request());
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConstant.BASE_URL)
                .client(okHttpClient)
                .build();

        api = retrofit.create(API.class);

    }


    public static CEOClient getInstance(){
        if (instance ==null){
            synchronized (CEOClient.class){
                if (instance ==null){
                    instance = new CEOClient();

                }
        }
    }
        return instance;

    }



    public static API getApi(){

        return getInstance().api;
    }

}
