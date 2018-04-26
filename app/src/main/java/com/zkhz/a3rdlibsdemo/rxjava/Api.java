package com.zkhz.a3rdlibsdemo.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

//93ba680b61c343b8a0777a045c0faab0

public interface Api {

    @GET("index")
    Observable<NewsData> getNewsData(@Query("type") String type,@Query("key")String key);
}
