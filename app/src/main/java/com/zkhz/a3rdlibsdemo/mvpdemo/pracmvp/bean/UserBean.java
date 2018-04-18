package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.bean;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class UserBean {

    /**
     * (1)首先我们需要一个UserBean，用来保存用户信息
     */

    private String user;
    private String pwd;

    public UserBean(String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
