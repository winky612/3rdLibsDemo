package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public interface FindItemsInteractor {


    /**
     * Model层
     *
     * 这里一个接口用来返回拿到的数据，接口中的方法将数据传入。
     * 在获取数据的方法里将这个接口传入，接口的实例化是在代理中实现的。
     */

    interface OnFinishedListener{

        void onFinished(List<String> items);

    }

    void findItems(OnFinishedListener listener);




}
