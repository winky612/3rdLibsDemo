package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public interface DemoView {

    void showProgress();
    void hideProgress();
    void setItems(List<String> items);
    void showMessage(String message);
}
