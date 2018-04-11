package com.zkhz.a3rdlibsdemo.juherv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public interface NewsApi {

    @GET("index")
    Call<Data> getNewsData(
            @Query("type")String type,
            @Query("key")String key
    );





}
