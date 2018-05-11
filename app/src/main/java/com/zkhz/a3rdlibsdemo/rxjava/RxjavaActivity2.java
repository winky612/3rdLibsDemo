package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class RxjavaActivity2 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;

    private String TAG="RxjavaActivity2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_day01)
    public void onViewClicked() {
        rxJavaDay02();
    }

    private void rxJavaDay02() {

        //当我们在主线程中去创建一个上游Observable来发送事件, 则这个上游默认就在主线程发送事件.
        //当我们在主线程去创建一个下游Observer来接收事件, 则这个下游默认就在主线程中接收事件

        //1--创建被观察者Observable(上游 事件产生者)
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "Observable thread is: "+Thread.currentThread().getName());
                Log.d(TAG, "emit 1 ");
                e.onNext(1);

            }
        });

        //2--创建一个观察者Observer(下游 事件接收者)
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Log.i(TAG, "Observer thread is: "+Thread.currentThread().getName());
                Log.d(TAG, "accept: "+integer);
            }
        };


         /*
          * 3-
          * 调度器以一种最简单的方式将多线程用在你的Apps的中。它们是RxJava重要的一部分并能很好地与Observables协同工作。
          * 它们无需处理实现、同步、线程、平台限制、平台变化而可以提供一种灵活的方式来创建并发程序。
          * RxJava提供了5种调度器：.io() .computation() .immediate() .newThread() .trampoline()
          *
          * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
          *
          * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略
          * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
          *
          */
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() { //doOnNext()----它的作用是让!订阅者!在接收到数据之前干点有意思的事情
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());

                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);




    }
}
