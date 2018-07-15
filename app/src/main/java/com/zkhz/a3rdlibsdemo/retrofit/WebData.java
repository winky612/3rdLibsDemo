package com.zkhz.a3rdlibsdemo.retrofit;

import com.zkhz.a3rdlibsdemo.retrofit.entity.FullTimeEntity;

/**
 * Created by wk on 2018/7/15.
 */

public class WebData<T> {

    private String success;
    private String msg;
    private T data;

    //    @GET("")
//    Call<WebData<FullTimeEntity>> getFull(@Query(""));


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
