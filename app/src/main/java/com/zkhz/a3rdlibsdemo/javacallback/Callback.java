package com.zkhz.a3rdlibsdemo.javacallback;

/**
 * Created by Administrator on 2018/4/8 0008.
 *
 * 例子-----有一天小王遇到一个很难的问题，问题是“1 + 1 = ?”，就打电话问小李，小李一下子也不知道，就跟小王说，等我办完手上的事情，就去想想答案，
 *         小王也不会傻傻的拿着电话去等小李的答案吧，于是小王就对小李说，我还要去逛街，你知道了答案就打我电话告诉我，于是挂了电话，自己办自己的事情，
 *         过了一个小时，小李打了小王的电话，告诉他答案是2
 *
 * 这是一个回调接口 所谓回调：就是A类中调用B类中的某个方法C，然后B类中反过来调用A类中的方法D，D这个方法就叫回调方法
 *
 */

public interface Callback {

    /**
     *这个是小李知道答案时要调用的函数告诉小王，也就是回调函数
     *
     * @param result  答案
     */
    public void solve(String result);

}
