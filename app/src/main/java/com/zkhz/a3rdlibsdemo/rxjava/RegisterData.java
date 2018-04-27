package com.zkhz.a3rdlibsdemo.rxjava;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class RegisterData {
    private String success;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

    }
}
