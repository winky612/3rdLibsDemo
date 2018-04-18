package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.model;

import android.util.SparseArray;

import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.bean.UserBean;

/**
 * Created by Administrator on 2018/4/18 0018.
 *
 * 数据存储的模型层，只需要考虑怎么把数据存起来
 *
 */


public class UserModel implements IUserModel {

    //android的优化版hashmap，数据千条以下的时候效率高
    private SparseArray<UserBean> array=new SparseArray();


    @Override
    public void saveUser(int id, String name, String password) {

        array.append(id,new UserBean(name,password));
    }

    @Override
    public UserBean load(int id) {

        if (array.indexOfKey(id)>=0){
            return array.get(id);
        }else
        return null;
    }




}
