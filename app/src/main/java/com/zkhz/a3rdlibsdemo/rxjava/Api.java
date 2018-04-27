package com.zkhz.a3rdlibsdemo.rxjava;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

//93ba680b61c343b8a0777a045c0faab0

public interface Api {

    @GET("index")
    Observable<NewsData> getNewsData(@Query("type") String type,@Query("key")String key);


    //可以看到登录和注册返回的都是一个上游Observable, 而我们的flatMap操作符的作用就是把一个Observable转换为另一个Observable, 因此结果就很显而易见了:

    @POST("appuser/esCodeLogin")
    @FormUrlEncoded
    Observable<LoginData> getLoginData(@Query("phoneNum")String phoneNum);

    @POST("appuser/reg")
    @FormUrlEncoded
    Observable<RegisterData> getRegisterData(@Field("")String s);

}
