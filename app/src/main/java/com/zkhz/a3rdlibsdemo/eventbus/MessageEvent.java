package com.zkhz.a3rdlibsdemo.eventbus;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class MessageEvent {

    private String msg;

    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
