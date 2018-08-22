package com.zkhz.a3rdlibsdemo.retrofit;

import android.text.TextUtils;

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

public class Okhttp {
    public void set() {
        OkHttpClient client = new OkHttpClient.Builder()
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
                                            object.put(originFormBody.encodedName(i), ((FormBody) originBody).encodedValue(i));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    dataParamter = object == null ? null : object.toString();
                                    if (!TextUtils.isEmpty(dataParamter)) {
                                        FormBody newFormBody = new FormBody.Builder()
                                                .add("data", dataParamter)
                                                .build();
                                        chain.proceed(originRequest.newBuilder().put(newFormBody)
                                                .build());
                                    }
                                }
                                break;
                        }
                        return chain.proceed(chain.request());
                    }
                }).build();
    }
}
