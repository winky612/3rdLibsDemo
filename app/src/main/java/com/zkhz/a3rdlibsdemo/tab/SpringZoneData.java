package com.zkhz.a3rdlibsdemo.tab;

/**
 * Created by wk on 2018/7/25 0025
 *
 * 主页---赛区信息
 */
public class SpringZoneData {

    private String state;
    private String zoneName;

    public SpringZoneData(String state, String zoneName) {
        this.state = state;
        this.zoneName = zoneName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
