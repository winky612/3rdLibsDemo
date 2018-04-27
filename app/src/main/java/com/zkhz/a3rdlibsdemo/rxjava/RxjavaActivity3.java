package com.zkhz.a3rdlibsdemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 */

public class RxjavaActivity3 extends AppCompatActivity {
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

    private static String TAG = "RxjavaActivity3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_day01, R.id.btn_day02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_day01:
                rxJavaDay03();
                break;
            case R.id.btn_day02:
                rxJavaDay003();
                break;
        }


    }


    //非链式写法
    private void rxJavaDay03() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                e.onNext(1);
                e.onNext(2);
                e.onNext(3);

            }
        });

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Log.d(TAG, "accept: " + s);
            }
        };

        //变换操作符Map----它的作用就是对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化(这里是:将上游发过来的Integer变成String)
        observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {

                return "Change to" + integer;
            }
        }).subscribe(consumer);
    }


    //FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
    // 注意:flatMap并不保证事件的顺序 如果需要保证顺序则需要使用concatMap.
    //链式写法
    private void rxJavaDay003() {
        
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {

                List<String> list=new ArrayList<>();
                for (int i = 0; i <3; i++) {
                    list.add("FlatMap"+integer);
                }

              return Observable.fromIterable(list).delay(10, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: "+s);

            }
        });


    }

}
