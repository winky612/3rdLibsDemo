package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.model;

import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.bean.UserBean;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public interface IUserModel {


    /**
     * (3) Model也需要对这三个字段进行读写操作，
     * 并存储在某个载体内(这不是我们所关心的，可以存在内存、文件、数据库或者远程服务器，但对于Presenter及View无影响)
     */


    void saveUser(int id,String name,String password);
    UserBean load(int id);//通过id读取user信息,返回一个UserBean



}
