package com.zkhz.a3rdlibsdemo.retrofit.entity;

import java.util.List;

public class FullTimeEntity {

    //{"fullTimes":[2017-08-01,2018-08-03]}

    private List<String> fullTimes;

    public List<String> getFullTimes() {
        return fullTimes;
    }

    public void setFullTimes(List<String> fullTimes) {
        this.fullTimes = fullTimes;
    }
}
