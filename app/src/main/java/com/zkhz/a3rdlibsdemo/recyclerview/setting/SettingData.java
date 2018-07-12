package com.zkhz.a3rdlibsdemo.recyclerview.setting;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by wk on 2018/7/12.
 */

public class SettingData {

    private int icon;
    private int title;
    private String cache;

    public SettingData(int icon, int title) {
        this.icon = icon;
        this.title = title;
    }

    public SettingData(int icon, int title, String cache) {
        this.icon = icon;
        this.title = title;
        this.cache = cache;

    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

}
