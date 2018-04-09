package com.zkhz.a3rdlibsdemo.javacallback;

/**
 * Created by Administrator on 2018/4/8 0008.
 *
 * 小李
 */

public class Li {

    public void executeMessage(Callback callback,String ques){

        System.out.println("小王问的问题--->" + ques);

        //模拟小李办自己的事情需要很长时间
        for (int i = 0; i <100 ; i++) {
            
        }

        String result="答案是2";


        /**
         * 于是就打电话告诉小王，调用小王中的方法
         */
        callback.solve(result);

    }
}
