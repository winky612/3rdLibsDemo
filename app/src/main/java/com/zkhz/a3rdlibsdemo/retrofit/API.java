package com.zkhz.a3rdlibsdemo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public interface API {

    @GET()
    Call<String> getDataCall(@Query("")String fd);

}
