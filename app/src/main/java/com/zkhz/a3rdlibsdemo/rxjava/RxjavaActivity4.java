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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class RxjavaActivity4 extends AppCompatActivity {

    private static final String TAG ="RxjavaActivity4" ;
    @BindView(R.id.btn_day01)
    Button btnDay01;
    @BindView(R.id.btn_day02)
    Button btnDay02;

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

                rxJavaDay04();

                break;
            case R.id.btn_day02:
                break;
        }
    }


    /**
     * Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件
     * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据
     */
    private void rxJavaDay04() {

        Observable<Integer> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                Log.d(TAG, "emit 1");
                e.onNext(1);
                Thread.sleep(1000);

                Log.d(TAG, "emit 2");
                e.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "emit 3");
                e.onNext(3);
                Thread.sleep(1000);

                Log.d(TAG, "emit 4");
                e.onNext(4);
                Thread.sleep(1000);

                Log.d(TAG, "emit complete-1");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                Log.d(TAG, "emit A");
                e.onNext("A");
                Thread.sleep(1000);

                Log.d(TAG, "emit B");
                e.onNext("B");
                Thread.sleep(1000);

                Log.d(TAG, "emit C");
                e.onNext("C");
                Thread.sleep(1000);

                Log.d(TAG, "emit complete-2");
                e.onComplete();

            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {

                return "zip: "+integer+s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {

                Log.d(TAG, "onNext: "+value);

            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "onError: ");

            }

            @Override
            public void onComplete() {

                Log.d(TAG, "onComplete: ");

            }
        });










    }
}
