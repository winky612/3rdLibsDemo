package com.zkhz.a3rdlibsdemo.mvpdemo.gitmvpdemo.main;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;


/**
 *
 * 只有两个方法，也可以看成只有一个方法，就是拿到数据并通过回调接口返回。
 */


public class FindItemsInteractorImpl implements FindItemsInteractor {


    @Override
    public void findItems(final OnFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(creatArrayList());
            }
        },2000);

    }

    private List<String> creatArrayList() {
        return Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
    }
}
