package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.presenter;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public interface IPresenter {

    /**
     * p层接口，让v层只能持有一个p层的接口实例
     * p层应该实现的2个方法
     */

    void saveUser();
    void loadUser();


}
