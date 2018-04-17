package com.zkhz.a3rdlibsdemo.mvpdemo.immvp;

/**
 * Created by Administrator on 2018/4/16 0016.
 *
 * Model
 *
 */

public class UserService implements IUserService {

    //假设这就是查到的User的信息
    @Override
    public String search(int hashCode){
        return "User"+hashCode;
    }
}
