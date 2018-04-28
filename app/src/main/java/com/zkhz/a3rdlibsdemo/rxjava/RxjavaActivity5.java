package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class RxjavaActivity5 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

    private static String TAG = "RxjavaActivity5";
    @BindView(R.id.btn_day03)
    Button btnDay03;
    @BindView(R.id.btn_day04)
    Button btnDay04;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02, R.id.btn_day03, R.id.btn_day04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:

                rxJavaDay05();
                break;
            case R.id.btn_day02:

                rxJavaTest();
                break;

            case R.id.btn_day03:

                rxJavaTest02();
                break;
            case R.id.btn_day04:


                break;
        }
    }


    //演示zip存储事件---内存↑
    private void rxJavaDay05() {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                //无限循环发事件
                for (int i = 0; ; i++) {

                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());


        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        /**
         * zip给我们的水缸(保存发送的事件)! 它将每根水管发出的事件保存起来, 等两个水缸都有事件了之后就分别从水缸中取出一个事件来组合,
         * 当其中一个水缸是空的时候就处于等待的状态.
         * 这个水缸在Zip内部的实现就是用的队列保存事件,先进来的事件先取出来
         */
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {

                return integer + s;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.d(TAG, "accept: " + s);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.w(TAG, "accept:Throwable ", throwable);
                    }
                });


    }


    //上下游工作在同一个线程-----内存降了好多
    //why???-----当上下游工作在同一个线程中时, 这时候是一个同步的订阅关系, 也就是说上游每发送一个事件必须等到下游接收处理完了以后才能接着发送下一个事件.
    private void rxJavaTest() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; ; i++) {

                    e.onNext(i);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Thread.sleep(2000);
                Log.d(TAG, "accept: " + integer);
            }
        });

    }


    //上下游工作在不同线程----把上游切换到了IO线程中去, 下游到主线程去接收----内存↑
    //why???-----当上下游工作在不同的线程中时, 这时候是一个异步的订阅关系, 这个时候上游发送数据不需要等待下游接收,
    // 为什么呢, 因为两个线程并不能直接进行通信, 因此上游发送的事件并不能直接到下游里去, 这个时候就需要一个田螺姑娘来帮助它们俩, 这个田螺姑娘就是我们刚才说的水缸(Zip) !
    // 上游把事件发送到水缸里去, 下游从水缸里取出事件来处理, 因此, 当上游发事件的速度太快, 下游取事件的速度太慢, 水缸就会迅速装满, 然后溢出来, 最后就OOM了.

    //源头找到了, 只要有水缸(Zip), 就会出现上下游发送事件速度不平衡的情况, 因此当我们以后遇到这种情况时, 仔细思考一下水缸在哪里, 找到水缸, 你就找到了解决问题的办法.
    private void rxJavaTest02() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                for (int i = 0; ; i++) {
                    e.onNext(i);

                }}})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Thread.sleep(2000);
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }
}
